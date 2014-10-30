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
				if (kundeTO instanceof PrivatkundeTO) {
					PrivatkundeTO privatkundeTO = (PrivatkundeTO) kundeTO;
		
				Persistence.executeUpdateStatement(
						aConnection, 
						"INSERT INTO kundenverw_privatkunde VALUES ( " +
						"'"+ privatkundeTO.getVorname() + "'," +
						"'"+ privatkundeTO.getNachname() + "'," +
						"'"+ privatkundeTO.getStr() + "'," +
						"'"+ privatkundeTO.getNr() + "'," +
						"'"+ privatkundeTO.getPlz() + "',"+
						"'"+ privatkundeTO.getOrt() + "'," +
						"'"+ privatkundeTO.getGeschlecht() + "'," + 
						privatkundeTO.getKundennummer() +")");
				}
			
				for (Integer kontonr:kundeTO.getKonten()) {
					Persistence.executeUpdateStatement(aConnection,
							"INSERT INTO kundenverw_kontonummer VALUES (" +
							kontonr + ", " + kundeTO.getKundennummer() + ")"); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatenhaltungsException();
			} finally {
				Persistence.closeConnection(aConnection);
			}
		
		
	}

	public void kundendatenLoeschen(KundeTO kundeTO)
			throws DatenhaltungsException {
		
		Connection aConnection = Persistence.getConnection();
		try {
			
			if (kundeTO instanceof PrivatkundeTO)
				Persistence.executeUpdateStatement(aConnection,
				"delete from kundenverw_privatkunde where cusnumber = " + kundeTO.getKundennummer());
			else 
				Persistence.executeUpdateStatement(aConnection,
				"delete from kundenverw_geschaeftskunde where cusnumber = " + kundeTO.getKundennummer());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}

	public void kundendatenaendern(KundeTO kundeTO) throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		try {
			Persistence.executeUpdateStatement(aConnection, 
					"delete from kundenverw_kontonummer where cusnumber = " + kundeTO.getKundennummer());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		kundendatenLoeschen(kundeTO);
		kundendatenAnlegen(kundeTO);
		
		
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
					"SELECT * FROM kundenverw_privatkunde WHERE cusnumber =" + nummer);
				if (resultSet.next()) {
					kundeTO = this.resultToKundeTO(resultSet, 'P');
					resultSet = 
						Persistence.executeQueryStatement(aConnection, 
								"SELECT * " +
								"FROM kundenverw_kontonummer " +
								"WHERE cusnumber = " + kundeTO.getKundennummer());
					
					while (resultSet.next())
						kundeTO.getKonten().add(resultSet.getInt("ACCOUNTNR"));
					
					return kundeTO;
				}
			resultSet.close();	
				
				
			resultSet = 
					Persistence.executeQueryStatement(
						aConnection, 
						"SELECT * FROM kundenverw_geschaeftskunde WHERE cusnumber =" + nummer);
			
			if (resultSet.next()) {
				kundeTO = this.resultToKundeTO(resultSet, 'G');
				resultSet = 
					Persistence.executeQueryStatement(aConnection, 
							"SELECT * " +
							"FROM kundenverw_kontonummer " +
							"WHERE cusnumber = " + kundeTO.getKundennummer());
			
				while (resultSet.next())
					kundeTO.getKonten().add(resultSet.getInt("ACCOUNTNR"));
			
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
	
	
	public Collection<KundeTO> kundendatenSuchenByAttribute(KundeTO kundeTO)
		throws DatenhaltungsException {

		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		KundeTO foundKundeTO;
		Collection<KundeTO> kundenTOListe = new ArrayList<KundeTO>();
		
		String suchString="";
		if (kundeTO.getNachname()!= null && !kundeTO.getNachname().isEmpty() )
			suchString = "lastname = '" + kundeTO.getNachname() +"'";
		if (kundeTO.getVorname()!= null && !kundeTO.getVorname ().isEmpty())
			suchString += suchString.isEmpty()? 
					"firstname = '" + kundeTO.getVorname() +"'":
					"AND firstname = '" + kundeTO.getVorname() +"'";						
		if (kundeTO.getStr()!= null && !kundeTO.getStr().isEmpty())
			suchString += suchString.isEmpty()? 
					"street = '" + kundeTO.getStr() +"'":
					"AND street = '" + kundeTO.getStr() +"'";
		if (kundeTO.getNr()!= null && !kundeTO.getNr().isEmpty())
			suchString += suchString.isEmpty()? 					
					"no = '" + kundeTO.getNr() +"'":
					"AND no = '" + kundeTO.getNr() +"'";
		if (kundeTO.getPlz()!= null && !kundeTO.getPlz().isEmpty())
			suchString += suchString.isEmpty()?
					"zipcode = '" + kundeTO.getPlz() +"'":
					"AND zipcode = '" + kundeTO.getPlz() +"'";
		if (kundeTO.getOrt()!= null && !kundeTO.getOrt().isEmpty())
			suchString += suchString.isEmpty()?
					"city = '" + kundeTO.getOrt() +"'":
					"AND city = '" + kundeTO.getOrt() +"'";
		
		try {
			if (kundeTO instanceof PrivatkundeTO) {
			
				if ( ((PrivatkundeTO) kundeTO).getGeschlecht() != null  && !((PrivatkundeTO) kundeTO).getGeschlecht().isEmpty())
					suchString += " sex = '" + ((PrivatkundeTO) kundeTO).getGeschlecht() +"'";
		
				if (suchString.isEmpty()) 
					suchString = "SELECT * FROM kundenverw_privatkunde";
				else
					suchString = "SELECT * FROM kundenverw_privatkunde WHERE " + suchString;
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
										"FROM kundenverw_kontonummer " +
										"WHERE cusnumber = " + kTO.getKundennummer());
			
				while (resultSet.next()) {
					kTO.getKonten().add(resultSet.getInt("ACCOUNTNR"));
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
			privatkundeTO.setGeschlecht(resultSet.getString("SEX"));
			kundeTO = privatkundeTO;
			
			kundeTO.setKundennummer(resultSet.getInt("CUSNUMBER"));
			kundeTO.setNachname(resultSet.getString("LASTNAME"));		
			kundeTO.setVorname(resultSet.getString("FIRSTNAME"));
			kundeTO.setStr(resultSet.getString("STREET"));
			kundeTO.setNr(resultSet.getString("NO"));
			kundeTO.setPlz(resultSet.getString("ZIPCODE"));
			kundeTO.setOrt(resultSet.getString("CITY"));
		
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		
		}
		return kundeTO;
	}

	
	

	

}
