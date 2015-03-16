package com.suwadi.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@Entity(name = "users")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class User extends DomainObject {
	private Long id;
	private String userName;
	private String hashedPassword;
	private String salt;
	private String password;
	private String confirmPassword;
	private Profile profile;
	private List<Role> roles;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof User) {
				User other = (User) obj;
				if (this.getId().longValue() == other.getId().longValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Transient
	public Collection<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (this.roles != null && !this.roles.isEmpty()) {
			for (Role authority : this.getRoles()) {
				System.out.println(authority.getName());
				authList.add(new GrantedAuthorityImpl(authority.getName()));
			}
		}

		return authList;
	}

	@Override
	public String toString() {
		// final BeanWrapper wrapper = new BeanWrapperImpl(this);
		// for (final PropertyDescriptor descriptor : wrapper
		// .getPropertyDescriptors()) {
		// try {
		// System.out.println(descriptor.getName() + ":"
		// + descriptor.getReadMethod().invoke(this, null));
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		// }
		return super.toString();
	}

}
