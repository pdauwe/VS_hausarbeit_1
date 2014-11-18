package awk.kundenverwaltung.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import awk.Persistence.Persistence;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.persistence.IKundenDatenzugriff;
import awk.DatenhaltungsException;


public class KundenDatenzugriff_DAO_Db_Einzelsatz implements IKundenDatenzugriff{

	

	@Override
	public void kundendatenAnlegen(KundeTO kundeTO)
			throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			PrivatkundeTO privatkundeTO = (PrivatkundeTO) kundeTO;
		
			String geburtstagsString =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(privatkundeTO.getGeburtsdatum());
			
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
							"'"+ geburtstagsString + "'," +
							"'"+ privatkundeTO.getGeschlecht() + "')"
							);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatenhaltungsException();
			} finally {
				Persistence.closeConnection(aConnection);
			}
	
	}

	@Override
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
	
	
	@Override
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
			privatkundeTO.setGeschlecht(resultSet.getString("geschlecht"));
			privatkundeTO.setBenutzerkennung(resultSet.getString("benutzerkennung"));
			privatkundeTO.setPasswort(resultSet.getString("passwort"));
			java.sql.Timestamp geburtstagsTimestamp = resultSet.getTimestamp("geburtsdatum");
			privatkundeTO.setGeburtsdatum(new Date(geburtstagsTimestamp.getTime()));
			kundeTO = privatkundeTO;
			
			kundeTO.setKundennummer(resultSet.getInt("k_nr"));
			kundeTO.setNachname(resultSet.getString("nachname"));		
			kundeTO.setVorname(resultSet.getString("vorname"));
			kundeTO.setStr(resultSet.getString("strasse"));
			kundeTO.setNr(resultSet.getString("hausnr"));
			kundeTO.setPlz(resultSet.getString("plz"));
			kundeTO.setOrt(resultSet.getString("ort"));
			
		
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		
		}
		return kundeTO;
	}

	
	@Override
	public KundeTO kundeSuchenFuerLoginUndPasswort(String benutzerkennung, String passwort) throws DatenhaltungsException{
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KundeTO kundeTO;
		try {
			resultSet = 
				Persistence.executeQueryStatement(
					aConnection, 
					"SELECT * FROM ha1_kv_kunde WHERE benutzerkennung = '" + benutzerkennung + "'" + "AND passwort = '" + passwort+"'");
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
			return null;
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		finally {
			Persistence.closeConnection(aConnection);
		}
	}

	@Override
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

	@Override
	public ArrayList<PrivatkundeTO> getKundenListe() throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		ArrayList<PrivatkundeTO> kunden = new ArrayList<PrivatkundeTO>();
		try{
			resultSet = Persistence.executeQueryStatement(aConnection, "SELECT k_nr, vorname, nachname, geburtsdatum FROM ha1_kv_kunde");
			
			while(resultSet.next()){
				PrivatkundeTO k = new PrivatkundeTO();
				k.setKundennummer(resultSet.getInt("k_nr"));
				k.setVorname(resultSet.getString("vorname"));
				k.setNachname(resultSet.getString("nachname"));
				
				java.sql.Timestamp geburtstagsTimestamp = resultSet.getTimestamp("geburtsdatum");
				k.setGeburtsdatum(new Date(geburtstagsTimestamp.getTime()));
				
				kunden.add(k);
			}
			
			return kunden;
		}catch(SQLException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}finally{
			Persistence.closeConnection(aConnection);
		}
	}

}
