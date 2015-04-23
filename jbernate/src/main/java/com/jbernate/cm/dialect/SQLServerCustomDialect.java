package com.jbernate.cm.dialect;

import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.type.StandardBasicTypes;

/**
 * NVARCHAR등에서 인식 오류 발생
 * @author phan98
 *
 */
public class SQLServerCustomDialect extends SQLServer2012Dialect{

	public SQLServerCustomDialect() {
		super();

		//registerColumnType( Types.NVARCHAR, 4000, "NVARCHAR($l char)" );
		//registerColumnType( Types.NVARCHAR, StringType.INSTANCE.getName() );
		
		/*
		registerColumnType(Types.VARCHAR, "nvarchar($l)");
		registerColumnType(Types.NVARCHAR, "nvarchar($l)");
        registerColumnType(Types.CLOB, "nvarchar(max)");
        */
		
		/*
		registerColumnType( Types.CLOB, "varchar(MAX)" );
	    registerColumnType( Types.LONGNVARCHAR, "varchar(MAX)" );
	    registerColumnType( Types.VARCHAR, "varchar(MAX)" );
	    registerColumnType( Types.VARCHAR, MAX_LENGTH, "varchar($l)" );
	    registerColumnType( Types.CLOB, "nvarchar(MAX)" );
	    registerColumnType( Types.LONGNVARCHAR, "nvarchar(MAX)" );
	    registerColumnType( Types.NVARCHAR, "nvarchar(MAX)" );
	    registerColumnType( Types.NVARCHAR, MAX_LENGTH, "nvarchar($l)" );
	    registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
	    registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());
	    */
		
		registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.BIT, "bit");
        registerColumnType(Types.CHAR, "nchar(1)");
        registerColumnType(Types.VARCHAR, 4000, "nvarchar($l)");
        registerColumnType(Types.VARCHAR, "nvarchar(max)");
        registerColumnType(Types.VARBINARY, 4000, "varbinary($1)");
        registerColumnType(Types.VARBINARY, "varbinary(max)");
        registerColumnType(Types.BLOB, "varbinary(max)");
        registerColumnType(Types.CLOB, "nvarchar(max)");
        
        registerColumnType(Types.NVARCHAR, 4000, "nvarchar($l)");
        registerColumnType(Types.NVARCHAR, "nvarchar(max)");
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
	}
	
	public String getTypeName(int code, int length, int precision, int scale) throws HibernateException {
		if(code != 2005) {
            return super.getTypeName(code, length, precision, scale);
        } else {
            return "ntext";
        }
    }
}