

Running DbGen:


Copy dbgen.bat to the same directory as your DbGen.xls. Add the appropriate arguments as specified below.


Arguments

-o Specifies a properties file to use, instead of the default properties in DBGEN_HOME.
        Example: -omy_dbgen.properties

-f Specifies a properties file to use, values will override those properties found in DBGEN_HOME.
        Example: -fmy_dbgen.properties

-h Specifies the location of DbGen, either this value or the environment value DBGEN_HOME must be set.
        Example: -hG:/IntPrj/DbGen

-x Specifies the name of the Excel file to parse. Defaults to DbGen.xls.
        Example: -xDbGen2.xls

-p A key=value property that will override any value in the properties file.
       For multiple properties, pass in the -p flag multiple times each with its own property.
        Example: -pcontext.delete.type.default=all

-d Specifies a dialect to use, if this value is given only those dialects specified will be genereated.
       For multiple dialects, pass in the -d flag multiple times each with its own dialect.
       The dialect must match the dialect prefix in the dbgen.properties file. Excluding this argument wil
       cause all supported dialects to be generated.
        Example: -ddialect.mssql

-s Specifies a single spread sheet name to generate..
        Example: -sExample


-k Enables console input to specify a case sensitive prefix match for what sheets to rerender
	Example: -k 

-e Specifies the passphrase for encrypted column data. If this value is not present columns
       will be written as alphanumeric text.
        Example: -elv84g@Kt3d*B9kls0f;?235f
