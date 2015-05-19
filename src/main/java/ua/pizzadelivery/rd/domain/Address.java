package ua.pizzadelivery.rd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a"),
	@NamedQuery(name="Address.findClientsByCity", query="SELECT a.client FROM Address a "
			+ "WHERE a.city = :city")
})
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name = "addresses_id")
	private int id;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "bld")
	private String bld;
	
	@Column(name = "apt")
	private String apt;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy="address")
	private Client client;
	
	public Address() {
		
	}
	
	public Address(String postalCode, String city, String street, String bld, String apt) {
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
		this.bld = bld;
		this.apt = apt;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getBld() {
		return bld;
	}
	public void setBld(String bld) {
		this.bld = bld;
	}

	public String getApt() {
		return apt;
	}
	public void setApt(String apt) {
		this.apt = apt;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; "; 
		if (client == null) {
			res += "CLIENT UNKNOWN";
		} else {
			res += client.getName();	
		}				
		res += "; " + postalCode + "; " + city + "; " + street + "; " 
				+ bld + "; " + apt + "}";
		
		return res;
	}
}
