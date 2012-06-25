/**
 * Copyright (C) 2009, 2012, Claus Nielsen, cn@cn-consult.dk
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package dk.clanie.hibernate.usertype;

import static org.hibernate.type.StandardBasicTypes.STRING;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.EnhancedUserType;

/**
 * Hibernate UserType for {@link java.net.URL}.
 * 
 * @author Claus Nielsen
 */
public class UrlUserType implements EnhancedUserType {

	public final static UrlUserType INSTANCE = new UrlUserType();

	private static final int[] SQL_TYPES = new int[] {
			Types.VARCHAR,
	};

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return URL.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		URL dtx = (URL) x;
		URL dty = (URL) y;

		return dtx.equals(dty);
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object stringValue = STRING.nullSafeGet(rs, names, session, owner);
		if (stringValue == null) {
			return null;
		}

		try {
			return new URL((String) stringValue);
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		String stringValue = value == null ? null : value.toString();
		STRING.nullSafeSet(st, stringValue, index, session);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		}
		try {
			return new URL(value.toString());
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public String objectToSQLString(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toXMLString(Object object) {
		return object.toString();
	}

	@Override
	public Object fromXMLString(String string) {
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
	}

}
