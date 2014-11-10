package awk.depotverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import awk.Persistence.Persistence;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
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
					//depotTO.setSaldo(resultSet.getDouble("balance"));
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
						
//						WertpapiertransaktionTO aKtoBew = new WertpapiertransaktionTO(depotTO,
//								resultSet.getString("type").charAt(0),
//								resultSet.getDouble("amount"));
//						
//						depotTO.getWertpapiertransaktionen().add(aKtoBew);
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


	public void wertpapiertransaktionAnlegen(int depotnummer, WertpapiertransaktionTO wertpapiertransaktionTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into ha1_dv_wptransaktion VALUES ("
					+wertpapiertransaktionTO.getVorgangsnummer()+ "," +
					wertpapiertransaktionTO.getWertpapierTO().getNummer() + ", " +
					depotnummer + "," +
					wertpapiertransaktionTO.getMenge() + "," +
					wertpapiertransaktionTO.getPreis() + "," +
					"'" + wertpapiertransaktionTO.getDate().toString() + "'," +
					"'" + wertpapiertransaktionTO.getTyp() + "'," +
					wertpapiertransaktionTO.getBoersenplatz() + ")");
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}

	}

	public void depotAnlegen(DepotTO depotTO)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"insert into ha1_dv_depot VALUES (" +
					depotTO.getDepotNr() + "," +
					depotTO.getInhaberNr() + ", " +
					"'" + depotTO.getEroeffnungsdatum() + "')");
			
			this.speichereDepotKundeZuordnung(depotTO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
	}
	
	private void speichereDepotKundeZuordnung(DepotTO depotTO) throws DatenhaltungsException{
		Connection aConnection = Persistence.getConnection();
		try{
			Persistence.executeUpdateStatement(aConnection, "INSERT INTO ha1_kv_depotnummern VALUES(" + depotTO.getInhaberNr() + "," + depotTO.getDepotNr() + ")");
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
	}
	
	public void kontoSaldoaendern(DepotTO kontoTO)
		throws DatenhaltungsException {

//		Connection aConnection = Persistence.getConnection();
//		try {
//			Persistence.executeUpdateStatement(aConnection, 
//					"UPDATE kontenverw_konto " +
//					"SET balance = " + kontoTO.getSaldo() +
//					"WHERE accountnr = " + kontoTO.getDepotNr());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DatenhaltungsException();
//		}
	
	}


	public int maxKontonummer() throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		try {
			resultSet = 
				Persistence.executeQueryStatement(aConnection, 
					"SELECT max(d_nr) as max FROM ha1_dv_depot ");
			
			if (resultSet.next() )
					return resultSet.getInt("max");
				else
					return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}
	
	public int generiereVorgangsnummer() throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT max(vorgangsnummer) as max FROM vorgangsnummern");
			if(resultSet.next()){
				return resultSet.getInt("max");
			}else{
				return 0;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}

	public ArrayList<WertpapierTO> getWertpapiere() throws DatenhaltungsException{
		ArrayList<WertpapierTO> wps = new ArrayList<WertpapierTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * from ha1_dv_wertpapier");
			if(resultSet.next()){
				WertpapierTO wp = new WertpapierTO();
				wp.setNummer(resultSet.getInt("WP_NR"));
				wp.setArt(resultSet.getString("ART").toCharArray()[0]);
				wp.setBezeichnung(resultSet.getString("BEZ"));
				wps.add(wp);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		return wps;
	}





}
