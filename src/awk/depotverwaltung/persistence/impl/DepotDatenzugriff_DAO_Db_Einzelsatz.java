package awk.depotverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import awk.Persistence.Persistence;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.persistence.IDepotDatenzugriff;
import awk.DatenhaltungsException;


public class DepotDatenzugriff_DAO_Db_Einzelsatz implements IDepotDatenzugriff{

	/* liefert komplettes TO-Geflecht Konto/Kontobewegung */
	public DepotTO depotdatenLesenByKey(int depotnummer) throws DatenhaltungsException {
			
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		DepotTO depotTO = null;
		try {
				resultSet = 
					Persistence.executeQueryStatement(aConnection, 
						"SELECT accountnr, balance,owner " +
						"FROM kontenverw_konto " +
						"WHERE accountnr = " + depotnummer );
				while (resultSet.next()) {
					depotTO = new DepotTO();
					depotTO.setDepotNr(resultSet.getInt("accountnr"));
					depotTO.setSaldo(resultSet.getDouble("balance"));
					depotTO.setInhaberNr(resultSet.getInt("owner"));
				};
				
				if (depotTO!=null) {
					resultSet =
						Persistence.executeQueryStatement(aConnection, 
								"SELECT type, amount " +
								"FROM kontenverw_buchung " +
								"WHERE account = " + depotnummer + "");
					while ( resultSet.next() ) {
						System.out.println("type: "+resultSet.getString("type").charAt(0));
						System.out.println("amount: "+resultSet.getDouble("amount"));
						
						WertpapiertransaktionTO aKtoBew = new WertpapiertransaktionTO(depotTO,
								resultSet.getString("type").charAt(0),
								resultSet.getDouble("amount"));
						
						depotTO.getWertpapiertransaktionen().add(aKtoBew);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}
		
		return depotTO;
	}


	public void buchungsdatenAnlegen(int kontonummer, WertpapiertransaktionTO kontobewegungTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into kontenverw_buchung VALUES (" +
					"'" +kontobewegungTO.getTyp() + "'," +
					kontobewegungTO.getPreis() + ", " +
					kontonummer + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}

	}

	public void kontodatenAnlegen(DepotTO kontoTO)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into kontenverw_konto VALUES (" +
					kontoTO.getDepotNr() + "," +
					kontoTO.getSaldo() + ", " +
					kontoTO.getInhaberNr() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}	
	}
	
	public void kontoSaldoaendern(DepotTO kontoTO)
		throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"UPDATE kontenverw_konto " +
					"SET balance = " + kontoTO.getSaldo() +
					"WHERE accountnr = " + kontoTO.getDepotNr());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	
	}


	public int maxKontonummer() throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		try {
			resultSet = 
				Persistence.executeQueryStatement(aConnection, 
					"SELECT max(accountnr) as max FROM kontenverw_konto ");
			
			if (resultSet.next() )
					return resultSet.getInt("max");
				else
					return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}



}
