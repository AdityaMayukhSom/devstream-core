## Server Setup

1. Make sure to install Java 21 compatible JDK and JRE. The initial project is setup with `Amazon Coretto`. Make sure to also install a container runtime such as Docker or Podman to run local database instances and testing for production environment. You'll also need to have a working setup of Git. Additionally, you may need to install [Microsoft Visual Studio Build Tools](https://visualstudio.microsoft.com/downloads/?q=build+tools)  or local database setup, but that is not always required.

2. Clone the GitHub repository from the [Link](https://github.com/AdityaMayukhSom/devstream-core) into your local system. 

3. The `main` branch is protected; hence you cannot directly push to the `main` branch. You need to create branches using `git branch <branch-name>`, checkout that branch using `git checkout <branch-name>` , make changes into the branch, commit the changes into the branch, then push the branch into remote repository. 

4. After that you are required to create a pull request with details regarding the changes made into the branch. Once the PR has been created, it'll be reviewed and if the testing goes well, it'll be merged.

5. To run the spring boot application in development mode, setup an environment variable `SPRING_PROFILES_ACTIVE` to `development` using the following command. The following command works for Windows 11 setup.

   ```dockerfile
   set SPRING_PROFILES_ACTIVE development
   ```
6.  [\[Spring Boot bootRun with continuous build\]](https://stackoverflow.com/questions/52092504/spring-boot-bootrun-with-continuous-build)

Before running the application, populate the `.env` file following the `.env.example` file.
Then source the environment variables using the `read-env-file.ps1` script found in scripts directory
using the command `Read-Env-File .env`.  This is required as we use environment variables in the `application.yml`
file, but not required in case of docker compose as it can directly use the env file using `env_file` property.

To have `Read-Enf-File` or `New-Env-Example` command working in powershell, we first need to source the scripts using `. ./scripts/read-env-file.ps1` or `. ./scripts/new-env-example.ps1` command.

```sh
./gradlew build --continuous
./gradlew bootRun
```