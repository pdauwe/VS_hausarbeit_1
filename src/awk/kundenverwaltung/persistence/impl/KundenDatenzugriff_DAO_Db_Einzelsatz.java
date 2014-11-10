package awk.kundenverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import awk.Persistence.Persistence;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.persistence.IKundenDatenzugriff;
import awk.DatenhaltungsException;


public class KundenDatenzugriff_DAO_Db_Einzelsatz implements IKundenDatenzugriff{

	

	public void kundendatenAnlegen(KundeTO kundeTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			PrivatkundeTO privatkundeTO = (PrivatkundeTO) kundeTO;
			
			
			//oracle.sql.DATE sqlDate = new oracle.sql.DATE(privatkundeTO.getGeburtsdatum());
			
			Persistence.executeUpdateStatement(
					aConnection, 
					"INSERT INTO ha1_kv_kunde VALUES (" +
							"'"+ privatkundeTO.getKundennummer() + "'," +
							"'"+ privatkundeTO.getBenutzerkennung() + "'," +
							"'"+ privatkundeTO.getPasswort() + "'," +
							"'"+ privatkundeTO.getNachname() + "'," +
							"'"+ privatkundeTO.getVorname() + "'," +
							"'"+ privatkundeTO.getStr() + "'," +
							"'"+ privatkundeTO.getNr() + "'," +
							"'"+ privatkundeTO.getPlz() + "'," +
							"'"+ privatkundeTO.getOrt() + "'," +
							"'"+ privatkundeTO.getGeburtsdatum() + "'," +
							"'"+ privatkundeTO.getGeschlecht() + "')"
							);
				
			
//				for (Integer depotnr:kundeTO.getDepots()) {
//					Persistence.executeUpdateStatement(aConnection,
//							"INSERT INTO ha1_kv_depotnummern VALUES (" +
//							"'"+kundeTO.getKundennummer() + "', '" + depotnr + "')"); 
//				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatenhaltungsException();
			} finally {
				Persistence.closeConnection(aConnection);
			}
	
	}

	public KundeTO kundendatenSuchenByKey(int nummer)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KundeTO kundeTO;
		try {
			resultSet = 
				Persistence.executeQueryStatement(
					aConnection, 
					"SELECT * FROM ha1_kv_kunde WHERE k_nr =" + nummer);
				if (resultSet.next()) {
					kundeTO = this.resultToKundeTO(resultSet, 'P');
					resultSet = 
						Persistence.executeQueryStatement(aConnection, 
								"SELECT * " +
								"FROM ha1_kv_depotnummern " +
								"WHERE k_nr = " + kundeTO.getKundennummer());
					
					while (resultSet.next())
						kundeTO.getDepots().add(resultSet.getInt("d_nr"));
					
					return kundeTO;
				}
			resultSet.close();	
	
			return null;
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		finally {
			Persistence.closeConnection(aConnection);
		}
	}
	
	
	public Collection<KundeTO> kundendatenSuchenByAttribute(KundeTO kundeTO)
		throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KundeTO foundKundeTO;
		Collection<KundeTO> kundenTOListe = new ArrayList<KundeTO>();
		
		String suchString="";
		if (kundeTO.getNachname()!= null && !kundeTO.getNachname().isEmpty() )
			suchString = "nachname = '" + kundeTO.getNachname() +"'";
		if (kundeTO.getVorname()!= null && !kundeTO.getVorname ().isEmpty())
			suchString += suchString.isEmpty()? 
					"vorname = '" + kundeTO.getVorname() +"'":
					"AND vorname = '" + kundeTO.getVorname() +"'";						
		if (kundeTO.getStr()!= null && !kundeTO.getStr().isEmpty())
			suchString += suchString.isEmpty()? 
					"strasse = '" + kundeTO.getStr() +"'":
					"AND strasse = '" + kundeTO.getStr() +"'";
		if (kundeTO.getNr()!= null && !kundeTO.getNr().isEmpty())
			suchString += suchString.isEmpty()? 					
					"hausnr = '" + kundeTO.getNr() +"'":
					"AND hausnr = '" + kundeTO.getNr() +"'";
		if (kundeTO.getPlz()!= null && !kundeTO.getPlz().isEmpty())
			suchString += suchString.isEmpty()?
					"plz = '" + kundeTO.getPlz() +"'":
					"AND plz = '" + kundeTO.getPlz() +"'";
		if (kundeTO.getOrt()!= null && !kundeTO.getOrt().isEmpty())
			suchString += suchString.isEmpty()?
					"ort = '" + kundeTO.getOrt() +"'":
					"AND ort = '" + kundeTO.getOrt() +"'";
		
		System.out.println("PENG");
		
		try {
			if (kundeTO instanceof PrivatkundeTO) {
			
				if ( ((PrivatkundeTO) kundeTO).getGeschlecht() != null  && !((PrivatkundeTO) kundeTO).getGeschlecht().isEmpty()){
					suchString += "AND geschlecht = '" + ((PrivatkundeTO) kundeTO).getGeschlecht() +"'";
				}
				
				if (suchString.isEmpty()) {
					
					suchString = "SELECT * FROM ha1_kv_kunde";
				}
				else{
					suchString = "SELECT * FROM ha1_kv_kunde WHERE " + suchString;
				}
				resultSet = 
					Persistence.executeQueryStatement(aConnection,suchString);
				while (resultSet.next()) {
					foundKundeTO = this.resultToKundeTO(resultSet, 'P');
					kundenTOListe.add(foundKundeTO);
				}
			}
		
			
			for (KundeTO kTO:kundenTOListe) {
				resultSet = 
						Persistence.executeQueryStatement(aConnection, 
								"SELECT * " +
										"FROM ha1_dv_depotnummern " +
										"WHERE k_nr = " + kTO.getKundennummer());
			
				while (resultSet.next()) {
					kTO.getDepots().add(resultSet.getInt("d_nr"));
				}
			}
		} catch (SQLException e) {
		e.printStackTrace();
		throw new DatenhaltungsException();
		}  finally {
		Persistence.closeConnection(aConnection);
		}
	return kundenTOListe;
		
}

	private KundeTO resultToKundeTO(ResultSet resultSet, char typ) throws DatenhaltungsException {
		PrivatkundeTO privatkundeTO;
		KundeTO kundeTO;
		try {
			privatkundeTO = new PrivatkundeTO();
			privatkundeTO.setGeschlecht(resultSet.getString("GESCHLECHT"));
			privatkundeTO.setBenutzerkennung(resultSet.getString("BENUTZERKENNUNG"));
			privatkundeTO.setPasswort(resultSet.getString("PASSWORT"));
			privatkundeTO.setGeburtsdatum(resultSet.getString("GEBURTSDATUM"));
			kundeTO = privatkundeTO;
			
			kundeTO.setKundennummer(resultSet.getInt("K_NR"));
			kundeTO.setNachname(resultSet.getString("NACHNAME"));		
			kundeTO.setVorname(resultSet.getString("VORNAME"));
			kundeTO.setStr(resultSet.getString("STRASSE"));
			kundeTO.setNr(resultSet.getString("HAUSNR"));
			kundeTO.setPlz(resultSet.getString("PLZ"));
			kundeTO.setOrt(resultSet.getString("ORT"));
			
		
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		
		}
		return kundeTO;
	}

	
	public KundeTO kundeSuchenFuerLoginUndPasswort(String benutzerkennung, String passwort) throws DatenhaltungsException{
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KundeTO kundeTO;
		try {
			resultSet = 
				Persistence.executeQueryStatement(
					aConnection, 
					"SELECT * FROM kundenverw_privatkunde WHERE benutzerkennung =" + benutzerkennung + "AND passwort =" + passwort);
				if (resultSet.next()) {
					kundeTO = this.resultToKundeTO(resultSet, 'P');
					resultSet = 
						Persistence.executeQueryStatement(aConnection, 
								"SELECT * " +
								"FROM kundenverw_kontonummer " +
								"WHERE cusnumber = " + kundeTO.getKundennummer());
					
					while (resultSet.next())
						kundeTO.getDepots().add(resultSet.getInt("ACCOUNTNR"));
					
					return kundeTO;
				}
			return null;
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		finally {
			Persistence.closeConnection(aConnection);
		}
	}

	public int getMaxKundennummer() throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		int max = 0;
		
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT MAX(k_nr) as max from ha1_kv_kunde");
			if(resultSet.next()){
				max = resultSet.getInt("max");
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
		
		return max + 1;
	}
	
	

}
