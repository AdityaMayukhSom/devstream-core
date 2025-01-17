function Read-Env-File {
    [CmdletBinding()]
    param(
        [Parameter(Mandatory = $false)]
        [string]$FilePath = ".env",

        [Parameter(Mandatory = $false)]
        [switch]$PrintValues
    )

    try {
        if (!(Test-Path -Path $FilePath)) {
            Write-Warning "File ${FilePath} not found."
            return
        }

        Get-Content $FilePath | Where-Object { $_.trim() -ne "" } | ForEach-Object {
            [string]$name, [string]$value = $_.split('=')
            # https://www.sharepointdiary.com/2020/07/powershell-foreach-loop-foreach-object-guide.html
            # However, When you use break or continue in ForEach-Object cmdlet,
            # the whole loop is terminated instead of skipping the current iteration.
            # You have to use “Return” instead of “Continue”.
            if ([string]::IsNullOrWhiteSpace($name) -or [string]::IsNullOrWhiteSpace($value) -or $name.StartsWith('#')) {
                return
            }

            Set-Content env:$name $value

            # INFO: This script prints out the environment variables in the console, which may include sensitive informations.
            # Please either comment the following line or remove it completely before running it on production.
            if ($PrintValues) {
                Write-Output  "$($name.PadRight(48, ' ')) $value"
            }
        }
    }
    catch {
        Write-Error "An error occurred while reading the environment file: $_"
    }
}
