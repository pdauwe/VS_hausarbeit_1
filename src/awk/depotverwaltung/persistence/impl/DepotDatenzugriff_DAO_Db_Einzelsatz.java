package awk.depotverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import awk.Persistence.Persistence;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.persistence.IDepotDatenzugriff;
import awk.DatenhaltungsException;


public class DepotDatenzugriff_DAO_Db_Einzelsatz implements IDepotDatenzugriff{

	/* liefert komplettes TO-Geflecht Konto/Kontobewegung */
	@Override
	public DepotTO depotdatenLesenByKey(int depotnummer) throws DatenhaltungsException {
			
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		DepotTO depotTO = null;
		try {
				resultSet = 
					Persistence.executeQueryStatement(aConnection, 
						"SELECT *" +
						"FROM ha1_dv_depot " +
						"WHERE d_nr = " + depotnummer );
				
				while (resultSet.next()) {
					depotTO = new DepotTO();
					depotTO.setDepotNr(resultSet.getInt("d_nr"));
					depotTO.setInhaberNr(resultSet.getInt("k_nr"));
					depotTO.setEroeffnungsdatum(resultSet.getString("erodatum"));
				};
				
				if (depotTO!=null) {
					resultSet =
						Persistence.executeQueryStatement(aConnection, 
								"SELECT * " +
								"FROM ha1_dv_wptransaktion " +
								"WHERE d_nr = " + depotnummer + "");
					while ( resultSet.next() ) {
						
						WertpapiertransaktionTO wpt = new WertpapiertransaktionTO();
						
						
						wpt.setVorgangsnummer(resultSet.getInt("wpt_nr"));
						wpt.setBoersenplatz(resultSet.getInt("bplatz"));
						wpt.setTyp(resultSet.getString("TART").charAt(0));
						wpt.setDate(resultSet.getString("datum"));
						wpt.setMenge(resultSet.getInt("Menge"));
						wpt.setPreis(resultSet.getDouble("Preis"));
					
						int wpnummer = resultSet.getInt("wp_nr");
						WertpapierTO wp = new WertpapierTO();
						
						ResultSet resultSet2 = Persistence.executeQueryStatement(aConnection, "SELECT * FROM ha1_dv_wertpapier WHERE wp_nr = " + wpnummer);
						
						if(resultSet2.next()){
							wp.setBezeichnung(resultSet2.getString("BEZ"));
							wp.setArt(resultSet2.getString("ART"));
							wp.setNummer(wpnummer);
						}
						
						wpt.setWertpapierTO(wp);;
						
						depotTO.getWertpapiertransaktionen().add(wpt);
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


	@Override
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

	@Override
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
	
	@Override
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


	@Override
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
	
	@Override
	public int generiereVorgangsnummer() throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT max(wpt_nr) as max FROM ha1_dv_wptransaktion");
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

	@Override
	public ArrayList<WertpapierTO> getWertpapiere() throws DatenhaltungsException{
		ArrayList<WertpapierTO> wps = new ArrayList<WertpapierTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * from ha1_dv_wertpapier");
			while(resultSet.next()){
				WertpapierTO wp = new WertpapierTO();
				wp.setNummer(resultSet.getInt("WP_NR"));
				wp.setArt(resultSet.getString("ART"));
				wp.setBezeichnung(resultSet.getString("BEZ"));
				wps.add(wp);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		return wps;
	}
	
	public String getWertpapierBezeichnungFuerNuemmer(int nummer) throws DatenhaltungsException{
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT bez FROM ha1_dv_wertpapier WHERE wp_nr = " + nummer);
			
			if(resultSet.next()){
				return resultSet.getString("bez");
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return null;
	}


	@Override
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot(int depotnummer)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		ArrayList<Integer> wertpapiernummern = new ArrayList<Integer>();
		ArrayList<BestandContainerTO> bcs = new ArrayList<BestandContainerTO>();
		
		try{
			// Alle Wertpapiernummern auslesen
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT DISTINCT wp_nr FROM ha1_dv_wptransaktion WHERE d_nr = " + depotnummer);
			
			while(resultSet.next()){
				wertpapiernummern.add(resultSet.getInt("wp_nr"));
			}
			
			// Für jedes Wertpapier den Bestand ausrechnen
			for(Integer i : wertpapiernummern){
				int summeMenge = 0;
				double summeWert = 0.0;
				
				// Summe der Gekauften
				resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM ha1_dv_wptransaktion WHERE TART = 'K' AND wp_nr = " + i);
				while(resultSet.next()){
					summeMenge += resultSet.getInt("menge");
					summeWert += (resultSet.getInt("menge") * resultSet.getDouble("preis"));
				}
				
				// Summe der Verkauften
				resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM ha1_dv_wptransaktion WHERE TART = 'V' AND wp_nr = " + i);
				while(resultSet.next()){
					summeMenge -= resultSet.getInt("menge");
					summeWert -= resultSet.getInt("menge") * resultSet.getDouble("preis");
				}
				
				BestandContainerTO bc = new BestandContainerTO();
				bc.setNummer(i);
				bc.setMenge(summeMenge);
				bc.setSumme(summeWert);
				bc.setBezeichung(this.getWertpapierBezeichnungFuerNuemmer(i));
				
				bcs.add(bc);
			}
		
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return bcs;
	}
	
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws DatenhaltungsException{
		ArrayList<WertpapiertransaktionTO> wps = new ArrayList<WertpapiertransaktionTO>();
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT * FROM ha1_dv_wptransaktion WHERE d_nr = " + depotnummer);
			
			while(resultSet.next()){
				WertpapiertransaktionTO wpt = new WertpapiertransaktionTO();
				WertpapierTO wp = new WertpapierTO();
				
				wpt.setVorgangsnummer(resultSet.getInt("wpt_nr"));
				wpt.setPreis(resultSet.getDouble("preis"));
				wpt.setMenge(resultSet.getInt("menge"));
				wpt.setDate(resultSet.getString("datum"));
				wpt.setTyp(resultSet.getString("tart").charAt(0));
				
				wp.setNummer(resultSet.getInt("wp_nr"));
				wpt.setWertpapierTO(wp);
				
				wps.add(wpt);
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		return wps;
	}
}
