package awk.kontenverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import awk.Persistence.Persistence;
import awk.kontenverwaltung.entity.KontoTO;
import awk.kontenverwaltung.entity.KontobewegungTO;
import awk.kontenverwaltung.persistence.IKontoDatenzugriff;
import awk.DatenhaltungsException;


public class KontoDatenzugriff_DAO_Db_Einzelsatz implements IKontoDatenzugriff{

	/* liefert komplettes TO-Geflecht Konto/Kontobewegung */
	public KontoTO kontendatenLesenByKey(int kontonummer) throws DatenhaltungsException {
			
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KontoTO kontoTO = null;
		try {
				resultSet = 
					Persistence.executeQueryStatement(aConnection, 
						"SELECT accountnr, balance,owner " +
						"FROM kontenverw_konto " +
						"WHERE accountnr = " + kontonummer );
				while (resultSet.next()) {
					kontoTO = new KontoTO();
					kontoTO.setKontoNr(resultSet.getInt("accountnr"));
					kontoTO.setSaldo(resultSet.getDouble("balance"));
					kontoTO.setInhaberNr(resultSet.getInt("owner"));
				};
				
				if (kontoTO!=null) {
					resultSet =
						Persistence.executeQueryStatement(aConnection, 
								"SELECT type, amount " +
								"FROM kontenverw_buchung " +
								"WHERE account = " + kontonummer + "");
					while ( resultSet.next() ) {
						System.out.println("type: "+resultSet.getString("type").charAt(0));
						System.out.println("amount: "+resultSet.getDouble("amount"));
						
						KontobewegungTO aKtoBew = new KontobewegungTO(kontoTO,
								resultSet.getString("type").charAt(0),
								resultSet.getDouble("amount"));
						
						kontoTO.getKontobewegungen().add(aKtoBew);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		} finally {
			Persistence.closeConnection(aConnection);
		}
		
		return kontoTO;
	}


	public void buchungsdatenAnlegen(int kontonummer, KontobewegungTO kontobewegungTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into kontenverw_buchung VALUES (" +
					"'" +kontobewegungTO.getTyp() + "'," +
					kontobewegungTO.getBetrag() + ", " +
					kontonummer + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}

	}

	public void kontodatenAnlegen(KontoTO kontoTO)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into kontenverw_konto VALUES (" +
					kontoTO.getKontoNr() + "," +
					kontoTO.getSaldo() + ", " +
					kontoTO.getInhaberNr() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}	
	}
	
	public void kontoSaldoaendern(KontoTO kontoTO)
		throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"UPDATE kontenverw_konto " +
					"SET balance = " + kontoTO.getSaldo() +
					"WHERE accountnr = " + kontoTO.getKontoNr());
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
