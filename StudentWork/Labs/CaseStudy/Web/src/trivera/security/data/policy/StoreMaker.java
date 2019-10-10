/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2018 Kim Morello
 * </p>
 * @author Kim Morello.
 */

package trivera.security.data.policy;

import java.io.*;
import java.util.*;

/**
 * This class is used to set up a serialized policy store for use 
 * during labs.  Run to write objects to file for later reading in.
 *
 * @version  Training Course
 */

public class StoreMaker {
	/********************************************************************/
	/*************************Variables**********************************/
	/********************************************************************/
	private String dataFile = "data.ser";

	/********************************************************************/
	/*************************Constructor********************************/
	/********************************************************************/

	/**
	* Policy constructor sets up a store maker.
	*/
	public StoreMaker() {
		super();

	}

	/********************************************************************/
	/******************Getters and Setters*******************************/
	/********************************************************************/

	public static void main(String[] args) {
		StoreMaker store = new StoreMaker();
		store.writeStore();
	}

	
	 @SuppressWarnings("unchecked")

	public void writeStore() {
		try {
			FileOutputStream fos = new FileOutputStream(dataFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			//Start Policy
			Policy thisPolicy = new Policy();
			thisPolicy.setPolicyNumber("1");
			//Insured
			PersonInfo insured = new PersonInfo();
			insured.setSurname("Windsor");
			insured.setGivenName("James");
			insured.setBirthDate("1962-06-01");
			insured.setAddr1("3678 First Ave");
			insured.setCity("Columbus");
			insured.setStateProv("ID");
			insured.setPostalCode("83322");
			insured.setGenderCd("M");
			insured.setMaritalStatusCd("M");
			thisPolicy.setInsured(insured);
			//End of Insured
			//Drivers
			Vector drivers = thisPolicy.getDrivers();
			Driver thisDriver = new Driver();
			PersonInfo driverInfo = new PersonInfo();
			driverInfo.setSurname("Windsor");
			driverInfo.setGivenName("James");
			driverInfo.setBirthDate("1962-06-01");
			driverInfo.setAddr1("3678 First Ave");
			driverInfo.setCity("Columbus");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83322");
			driverInfo.setGenderCd("M");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1978-06-03");
			thisDriver.setLicenseNumber("112233G");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Windsor");
			driverInfo.setGivenName("Mary");
			driverInfo.setBirthDate("1964-11-15");
			driverInfo.setAddr1("3678 First Ave");
			driverInfo.setCity("Columbus");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83322");
			driverInfo.setGenderCd("F");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1980-12-03");
			thisDriver.setLicenseNumber("665577H");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Windsor");
			driverInfo.setGivenName("Scott");
			driverInfo.setBirthDate("1984-12-31");
			driverInfo.setAddr1("3678 First Ave");
			driverInfo.setCity("Columbus");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83322");
			driverInfo.setGenderCd("M");
			driverInfo.setMaritalStatusCd("S");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("2000-12-03");
			thisDriver.setLicenseNumber("876876R");
			thisDriver.setStateProvCd("ID");
			Vector accidents = thisDriver.getAccidentViolations();
			AccidentViolation thisAccident = new AccidentViolation();
			thisAccident.setViolationCd("12");
			thisAccident.setViolationDate("2002-08-18");
			thisAccident.setViolationDes("Speeding");
			accidents.add(thisAccident);
			thisAccident = new AccidentViolation();
			thisAccident.setViolationCd("12");
			thisAccident.setViolationDate("2001-05-03");
			thisAccident.setViolationDes("Speeding");
			accidents.add(thisAccident);
			thisDriver.setAccidentViolations(accidents);
			drivers.add(thisDriver);
			thisPolicy.setDrivers(drivers)			;
			//End of Drivers
			//Start of vehicles
			Vector vehicles = thisPolicy.getVehicles();
			Vehicle thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(0));
			thisVehicle.setModel("Dodge Caravan");
			thisVehicle.setModelYear("2002");
			thisVehicle.setCostNewAmt("22500");
			thisVehicle.setBodyTypeCd("04");
			thisVehicle.setPlateExpDate("2003-06");
			thisVehicle.setPurchaseDate("2002-05-25");
			thisVehicle.setVehicleIdNumber("1NXBR12EX1Z545917");
			thisVehicle.setVehicleUseCd("W");
			Vector coverages = thisVehicle.getCoverages();
			Coverage thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			Vector limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("COL");
			thisCoverage.setCoverageTermLmt("350");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(1));
			thisVehicle.setModel("Toyota Corolla");
			thisVehicle.setModelYear("2001");
			thisVehicle.setCostNewAmt("18650");
			thisVehicle.setBodyTypeCd("01");
			thisVehicle.setPlateExpDate("2003-08");
			thisVehicle.setPurchaseDate("2001-02-05");
			thisVehicle.setVehicleIdNumber("1NXFD12RX1Z325965");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("COL");
			thisCoverage.setCoverageTermLmt("350");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(2));
			thisVehicle.setModel("Subaru");
			thisVehicle.setModelYear("1980");
			thisVehicle.setCostNewAmt("7800");
			thisVehicle.setBodyTypeCd("05");
			thisVehicle.setPlateExpDate("2003-09");
			thisVehicle.setPurchaseDate("1980-06-01");
			thisVehicle.setVehicleIdNumber("1MGTR56EX1T675967");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);			
			thisPolicy.setVehicles(vehicles);
			//End of vehicles
			oos.writeObject(thisPolicy);
			//End Policy

			//Start Policy
			thisPolicy = new Policy();
			thisPolicy.setPolicyNumber("2");
			//Insured
			insured = new PersonInfo();
			insured.setSurname("Devo");
			insured.setGivenName("Sue");
			insured.setBirthDate("1935-06-01");
			insured.setAddr1("3678 Rollendet");
			insured.setCity("Atlanta");
			insured.setStateProv("ID");
			insured.setPostalCode("83312");
			insured.setGenderCd("F");
			insured.setMaritalStatusCd("W");
			thisPolicy.setInsured(insured);
			//End of Insured
			//Drivers
			drivers = thisPolicy.getDrivers();
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Devo");
			driverInfo.setGivenName("Sue");
			driverInfo.setBirthDate("1935-06-01");
			driverInfo.setAddr1("3678 Rollendet");
			driverInfo.setCity("Atlanta");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83312");
			driverInfo.setGenderCd("F");
			driverInfo.setMaritalStatusCd("W");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1952-06-03");
			thisDriver.setLicenseNumber("11FG");
			thisDriver.setStateProvCd("ID");
			accidents = thisDriver.getAccidentViolations();
			thisAccident = new AccidentViolation();
			thisAccident.setViolationCd("10");
			thisAccident.setViolationDate("1999-08-18");
			thisAccident.setViolationDes("Driving too slow.");
			accidents.add(thisAccident);
			thisDriver.setAccidentViolations(accidents);
			drivers.add(thisDriver);
			thisPolicy.setDrivers(drivers)			;
			//End of Drivers
			//Start of vehicles
			vehicles = thisPolicy.getVehicles();
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(0));
			thisVehicle.setModel("Buick Special");
			thisVehicle.setModelYear("1969");
			thisVehicle.setCostNewAmt("2800");
			thisVehicle.setBodyTypeCd("04");
			thisVehicle.setPlateExpDate("2003-08");
			thisVehicle.setPurchaseDate("1969-01-01");
			thisVehicle.setVehicleIdNumber("17");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("COL");
			thisCoverage.setCoverageTermLmt("350");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);			
			thisPolicy.setVehicles(vehicles);
			//End of vehicles
			oos.writeObject(thisPolicy);
			//End Policy

			//Start Policy
			thisPolicy = new Policy();
			thisPolicy.setPolicyNumber("3");
			//Insured
			insured = new PersonInfo();
			insured.setSurname("Baylor");
			insured.setGivenName("Doug");
			insured.setBirthDate("1953-06-01");
			insured.setAddr1("133 12th");
			insured.setCity("Ozone");
			insured.setStateProv("ID");
			insured.setPostalCode("83922");
			insured.setGenderCd("M");
			insured.setMaritalStatusCd("M");
			thisPolicy.setInsured(insured);
			//End of Insured
			//Drivers
			drivers = thisPolicy.getDrivers();
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Baylor");
			driverInfo.setGivenName("Doug");
			driverInfo.setBirthDate("1953-06-01");
			driverInfo.setAddr1("133 12th");
			driverInfo.setCity("Ozone");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83922");
			driverInfo.setGenderCd("M");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1968-06-03");
			thisDriver.setLicenseNumber("11dddd3G");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Baylor");
			driverInfo.setGivenName("Becky");
			driverInfo.setBirthDate("1964-08-15");
			driverInfo.setAddr1("133 12th");
			driverInfo.setCity("Ozone");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83922");
			driverInfo.setGenderCd("F");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1980-12-03");
			thisDriver.setLicenseNumber("666667H");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisPolicy.setDrivers(drivers)			;
			//End of Drivers
			//Start of vehicles
			vehicles = thisPolicy.getVehicles();
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(0));
			thisVehicle.setModel("Subaru Forester");
			thisVehicle.setModelYear("1998");
			thisVehicle.setCostNewAmt("19900");
			thisVehicle.setBodyTypeCd("05");
			thisVehicle.setPlateExpDate("2004-06");
			thisVehicle.setPurchaseDate("1997-11-25");
			thisVehicle.setVehicleIdNumber("1NXBR12EX1Z545977");
			thisVehicle.setVehicleUseCd("W");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("COL");
			thisCoverage.setCoverageTermLmt("350");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(1));
			thisVehicle.setModel("Chevrolet Belair");
			thisVehicle.setModelYear("1953");
			thisVehicle.setCostNewAmt("2000");
			thisVehicle.setBodyTypeCd("01");
			thisVehicle.setPlateExpDate("2003-08");
			thisVehicle.setPurchaseDate("1992-02-05");
			thisVehicle.setVehicleIdNumber("1NXFD12ddds325965");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisPolicy.setVehicles(vehicles);
			//End of vehicles
			oos.writeObject(thisPolicy);
			//End Policy
			
			//Start Policy
			thisPolicy = new Policy();
			thisPolicy.setPolicyNumber("4");
			//Insured
			insured = new PersonInfo();
			insured.setSurname("Bindle");
			insured.setGivenName("Dave");
			insured.setBirthDate("1943-06-01");
			insured.setAddr1("111 12th");
			insured.setCity("Ozone");
			insured.setStateProv("ID");
			insured.setPostalCode("83922");
			insured.setGenderCd("M");
			insured.setMaritalStatusCd("M");
			thisPolicy.setInsured(insured);
			//End of Insured
			//Drivers
			drivers = thisPolicy.getDrivers();
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Bindle");
			driverInfo.setGivenName("Dave");
			driverInfo.setBirthDate("1943-06-01");
			driverInfo.setAddr1("111 12th");
			driverInfo.setCity("Ozone");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83922");
			driverInfo.setGenderCd("M");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1958-06-03");
			thisDriver.setLicenseNumber("14G");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisDriver = new Driver();
			driverInfo = new PersonInfo();
			driverInfo.setSurname("Bindle");
			driverInfo.setGivenName("Joni");
			driverInfo.setBirthDate("1966-08-15");
			driverInfo.setAddr1("111 12th");
			driverInfo.setCity("Ozone");
			driverInfo.setStateProv("ID");
			driverInfo.setPostalCode("83922");
			driverInfo.setGenderCd("F");
			driverInfo.setMaritalStatusCd("M");
			thisDriver.setDriverInfo(driverInfo);
			thisDriver.setLicenseDt("1980-12-03");
			thisDriver.setLicenseNumber("999667H");
			thisDriver.setStateProvCd("ID");
			drivers.add(thisDriver);
			thisPolicy.setDrivers(drivers)			;
			//End of Drivers
			//Start of vehicles
			vehicles = thisPolicy.getVehicles();
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(0));
			thisVehicle.setModel("Ford F150");
			thisVehicle.setModelYear("1995");
			thisVehicle.setCostNewAmt("15000");
			thisVehicle.setBodyTypeCd("07");
			thisVehicle.setPlateExpDate("2004-09");
			thisVehicle.setPurchaseDate("2000-11-25");
			thisVehicle.setVehicleIdNumber("1NXBR12EYYYZ545977");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("COL");
			thisCoverage.setCoverageTermLmt("350");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisVehicle = new Vehicle();
			thisVehicle.setRatedDriver((Driver)drivers.get(1));
			thisVehicle.setModel("Jeep CJ5");
			thisVehicle.setModelYear("1972");
			thisVehicle.setCostNewAmt("3570");
			thisVehicle.setBodyTypeCd("05");
			thisVehicle.setPlateExpDate("2003-08");
			thisVehicle.setPurchaseDate("1992-02-05");
			thisVehicle.setVehicleIdNumber("1NXFD12PPPP325965");
			thisVehicle.setVehicleUseCd("H");
			coverages = thisVehicle.getCoverages();
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("LI");
			thisCoverage.setCoverageTermLmt("140");
			limits = thisCoverage.getLimits();
			limits.add("50000");
			limits.add("100000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("PD");
			thisCoverage.setCoverageTermLmt("210");
			coverages.add(thisCoverage);
			thisCoverage = new Coverage();
			thisCoverage.setCoverageCd("UM");
			thisCoverage.setCoverageTermLmt("42");
			limits = thisCoverage.getLimits();
			limits.add("15000");
			thisCoverage.setLimits(limits);
			coverages.add(thisCoverage);
			thisVehicle.setCoverages(coverages);
			vehicles.add(thisVehicle);
			thisPolicy.setVehicles(vehicles);
			//End of vehicles
			oos.writeObject(thisPolicy);
			//End Policy

			oos.close();
		} catch (Exception io) {
			io.printStackTrace();
		};
	}

}
