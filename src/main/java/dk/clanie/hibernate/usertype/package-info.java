@org.hibernate.annotations.TypeDefs({
	@org.hibernate.annotations.TypeDef(
			name = "url",
			typeClass = dk.clanie.hibernate.usertype.PersistentURL.class
	)
})
package dk.clanie.hibernate.usertype;
