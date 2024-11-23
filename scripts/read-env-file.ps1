function Read-Env-File([string]$filename) {
    Get-Content $filename | Where-Object { $_.trim() -ne "" } | ForEach-Object {
        [string]$name, [string]$value = $_.split('=')
        # https://www.sharepointdiary.com/2020/07/powershell-foreach-loop-foreach-object-guide.html
        # However, When you use break or continue in ForEach-Object cmdlet,
        # the whole loop is terminated instead of skipping the current iteration. 
        # You have to use “Return” instead of “Continue”.
        if ([string]::IsNullOrWhiteSpace($name) -or [string]::IsNullOrWhiteSpace($value) -or $name.StartsWith('#')) {
            return
        }
        Write-Output  "$($name.PadRight(48, ' ')) $value"
        Set-Content env:$name $value
    }
}
