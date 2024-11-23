function New-Env-Example {
    param (
        [string]$dotenvFilePath = ".env",
        [string]$dotenvExampleFilePath = ".env.example"
    )

    # constants, don't know the convention of using all caps or not
    $separator = "="
    $openingDelim = "<|"
    $closingDelim = "|>"

    # Check if .env file exists
    if (-not (Test-Path $dotenvFilePath)) {
        Write-Host "Could not read dotenv file: $dotenvFilePath"
        return
    }

    try {
        # Open .env and .env.example files
        $dotenvLines = Get-Content $dotenvFilePath
    }
    catch {
        Write-Host "Error reading dotenv file: $dotenvFilePath"
        return
    }

    try {
        $exampleFile = [System.IO.StreamWriter]::new($dotenvExampleFilePath)
    }
    catch {
        Write-Host "Could not open example dotenv file: $dotenvExampleFilePath"
        return
    }

    # Write header to .env.example
    $exampleGenTimeStr = (Get-Date).ToString("ddd MMM dd HH:mm:ss MST yyyy")
    $exampleFile.WriteLine("# AUTO-GENERATED FILE - DO NOT EDIT")
    $exampleFile.WriteLine("# Generated At $exampleGenTimeStr")
    $exampleFile.WriteLine("")

    foreach ($envLine in $dotenvLines) {
        $envLine = $envLine.Trim()
        if ($envLine -eq "") {
            # Skip empty lines
            continue
        }

        $envParts = $envLine -split $separator, 2
        if ($envParts.Count -ne 2) {
            Write-Host "Found in dotenv file: $envLine"
            try {
                $exampleFile.Close()
            }
            catch {
                Write-Host "Could not save example dotenv file: $dotenvExampleFilePath"

            }
            return
        }

        $envVarName = $envParts[0].Trim()
        $exampleEnvVarValue = $envVarName.ToLower() -replace "_", "-"
        $exampleEnvLine = "$envVarName$separator$openingDelim$exampleEnvVarValue$closingDelim"
        
        try {
            $exampleFile.WriteLine($exampleEnvLine)
        }
        catch {
            Write-Host "Could not write to example dotenv file: $dotenvExampleFilePath"
            try {
                $exampleFile.Close()
            }
            catch {
                Write-Host "Could not save example dotenv file: $dotenvExampleFilePath"

            }
            return
        }
    }

    try {
        # This closes and saves the generated .env.example file
        $exampleFile.Close()
    }
    catch {
        Write-Host "Could not save example dotenv file: $dotenvExampleFilePath"
    }
}
