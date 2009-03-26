/**
 * Copyright (C) 2009, Claus Nielsen, cn@cn-consult.dk
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

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;

/**
 * Hibernate UserType for {@link java.net.URL}.
 *
 * @author Claus Nielsen
 */
public class UrlUserType implements EnhancedUserType
{
	public final static UrlUserType INSTANCE = new UrlUserType();

	private static final int[] SQL_TYPES = new int[]
    {
        Types.VARCHAR,
    };

    public int[] sqlTypes()
    {
        return SQL_TYPES;
    }

    @SuppressWarnings("unchecked")
	public Class returnedClass()
    {
        return URL.class;
    }

    public boolean equals(Object x, Object y) throws HibernateException
	{
        if (x == y)
        {
            return true;
        }
        if (x == null || y == null)
        {
            return false;
        }
        URL dtx = (URL) x;
        URL dty = (URL) y;

        return dtx.equals(dty);
    }

    public int hashCode(Object object) throws HibernateException
    {
        return object.hashCode();
    }

    public Object nullSafeGet(ResultSet resultSet, String[] strings, Object object) throws HibernateException, SQLException
	{
		return nullSafeGet(resultSet, strings[0]);

	}

	public Object nullSafeGet(ResultSet resultSet, String string) throws SQLException, HibernateException
	{
		Object timestamp = Hibernate.STRING.nullSafeGet(resultSet, string);
		if (timestamp == null) {
			return null;
		}

		try {
			return new URL((String) timestamp);
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
	}


	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException
	{
		if (value == null) {
			Hibernate.STRING.nullSafeSet(preparedStatement, null, index);
		} else {
			URL url = (URL) value;

			Hibernate.STRING.nullSafeSet(preparedStatement, url.toString(), index);
		}
	}

    public Object deepCopy(Object value) throws HibernateException
    {
        if (value == null) {
            return null;
        }
        try {
			return new URL(value.toString());
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
    }

    public boolean isMutable()
    {
        return false;
    }

    public Serializable disassemble(Object value) throws HibernateException
    {
        return (Serializable) value;
    }

    public Object assemble(Serializable cached, Object value) throws HibernateException
    {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException
    {
        return original;
    }

	public String objectToSQLString(Object object)
	{
		throw new UnsupportedOperationException();
	}

	public String toXMLString(Object object)
	{
		return object.toString();
	}

	public Object fromXMLString(String string)
	{
		try {
			return new URL(string);
		} catch (MalformedURLException e) {
			throw new HibernateException("URL <-> String mapping error.", e);
		}
	}
}
