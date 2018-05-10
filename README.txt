Repertoire
A Digital Flashcard Learning Tool

Requirements:

* Java 8

* Netbeans 8.2 or greater

* GlassFish Server 4.1.1 or greater

* MySQL Server 15.1 or greater

  * Must have PCRE compiled with UTF support

 * git 1.9.1 or greater

___

Download and Setup

1. Open your console and navigate to where you want to keep the download

2. Clone the repository to your machine using the command: 

	git clone https://github.com/rndmorris/Repertoire.git

3. Start MySQL Server, set up your user account and permissions as needed

4. Execute the appropriate build script from Repertoire/Database

5. Open all three projects (Repertoire.JavaClient, Repertoire.Shared, and Repertoire.WebAPI) in Netbeans

6. Resolve any broken references, if any occur (See Resolving Broken References)

7. Right click each project (in the order below) and select "Clean and Build"

  * Repertoire.Shared

  * Repertoire.JavaClient

  * Repertoire.WebAPI

___

Running

1. Ensure MySQL Server is running

2. Go to the Services tab, expand the Servers menu, and start GlassFish server

3. In the Netbeans Projects tab, right click Repertoire.WebAPI and select "Deploy"

4. In the Netbeans Projects tab, right click Repertoire.JavaClient and select "Run"

___

Resolving Broken References

Sometimes references to projects and libraries will break. Right click a broken project and click the resolve button. Mappings and paths for everything are below:

* Repertoire.Shared : Repertoire/Repertoire.Shared

* json-simple-1.1.jar : Repertoire/ThirdParty/json-simple-1.1.jar

* mysql-connector-java-5.1.45-bin.jar : Repertoire/ThirdParty/mysql-connector-java-5.1.45/mysql-connector-java-5.1.45-bin.jar
