package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
	"time"
)

func main() {
	const seperator = "="
	const openingDelim = "<|"
	const closingDelim = "|>"

	dotenvFilePath := ".env"
	dotenvExampleFilePath := ".env.example"

	dotenvFile, err := os.Open(dotenvFilePath)
	if err != nil {
		log.Println("could not read dotenv file")
		return
	}
	defer dotenvFile.Close()

	exampleFile, err := os.Create(dotenvExampleFilePath)
	if err != nil {
		log.Println("could not open example dotenv file")
		return
	}
	defer exampleFile.Close()

	dotenvScanner := bufio.NewScanner(dotenvFile)
	exampleWriter := bufio.NewWriter(exampleFile)

	exampleGenTimeStr := time.Now().Format(time.RubyDate)
	exampleWriter.WriteString("# AUTO-GENERATED FILE - DO NOT EDIT\n")
	exampleWriter.WriteString(fmt.Sprintf("# Generated At %s\n\n", exampleGenTimeStr))

	for dotenvScanner.Scan() {
		envLine := strings.TrimSpace(dotenvScanner.Text())
		if envLine == "" {
			// line is an empty line, so skip this line
			continue
		}

		envParts := strings.Split(envLine, seperator)
		if len(envParts) != 2 {
			log.Println("found in dotenv file ", envLine)
			log.Println("each line in dotenv file should be of format <variable_name>=<variable_value>")
			return
		}

		envVarName := strings.TrimSpace(envParts[0])
		exampleEnvVarValue := strings.ReplaceAll(strings.ToLower(envVarName), "_", "-")
		exampleEnvLine := fmt.Sprintf("%s%s%s%s%s\n", envVarName, seperator, openingDelim, exampleEnvVarValue, closingDelim)
		_, err := exampleWriter.WriteString(exampleEnvLine)
		if err != nil {
			log.Println("could not write to example dotenv file")
			return
		}
	}

	err = exampleWriter.Flush()
	if err != nil {
		log.Println("could not flush example dotenv file")
		return
	}
}
