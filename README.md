# How to build application:
	1. go to parking-lot directory 
	2. ~/parking-lot/mvn clean install
	
# How to run application:
	1. go to ~/parking-lot/target directory after buiding application
 	2. java -jar ~/parking-lot/target/parking-lot-jar-with-dependencies.jar

# Limitations for file upload and functionality wise:
	1. supported vehicle's size: SMALL, MEDIUM, LARGE, EXTRA_LARGE
	2. supported vehicle's color: RED, WHITE, YELLOW, SILVER, BLACK, GREEN, BLUE


# For eclipse users, follow below steps:
	1. import project as maven project from git or local directory and build it use `mvn clean install`
	2. ParkingSystem.java is main class file to debug/run 
	
# For reffrence file format sample available in `src/test/resources/parking_lot_file_inputs.txt`
	