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
package dk.clanie.sql;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;

public class SqlDialectFactoryBean implements FactoryBean {

	private SqlDialect sqlDialect;
	
	@Required
	public void setSqlDialectClassName(String sqlDialectClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		sqlDialect = (SqlDialect) Thread.currentThread().getContextClassLoader().loadClass(sqlDialectClassName).newInstance();
	}

	@Override
	public Object getObject() throws Exception {
		return sqlDialect;
	}

	@Override
	public Class<SqlDialect> getObjectType() {
		return SqlDialect.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}