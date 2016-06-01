package by.pvt.aliushkevich.pojos;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Student implements Serializable {
	private static final long serialVersionUID = 3L;
	private int id;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private Set<Relation> relations = new HashSet<>();

	public Student() {
	}

	public Student(String firstName, String lastName, String login, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(unique = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval=true)
	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

	@Override
	public String toString() {
		return "Student ID " + id + ": " + firstName + " " + lastName + " (login: " + login + ")\n" + "relations: " +relations;
	}

}
