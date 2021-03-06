/**
 * Copyright (C) 2007-2009, Claus Nielsen, cn@cn-consult.dk
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
package dk.clanie.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import dk.clanie.core.BaseClass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BasePersistentClass extends BaseClass {

	private Long id;
	private Long version;

	/**
	 * Default constructor.
	 */
	public BasePersistentClass() {
	}

	/**
	 * Full constructor.
	 *
	 * @param id
	 * @param version
	 */
	protected BasePersistentClass(Long id, Long version) {
		this.id = id;
		this.version = version;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Version
	public Long getVersion() {
		return version;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	private void setVersion(Long version) {
		this.version = version;
	}

}
