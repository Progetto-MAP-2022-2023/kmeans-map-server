package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Questa classe rappresenta lo schema di una tabella del database, fornendo informazioni sulle colonne.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public class TableSchema {
    /** L'accesso al database. */
    DbAccess db;

    /**
     * Classe interna che rappresenta una colonna della tabella.
     */
    public class Column{
        /** Il nome della colonna. */
        private String name;

        /** Il tipo della colonna. */
        private String type;

        /**
         * Costruttore della classe {@code Column}.
         *
         * @param name Il nome della colonna.
         * @param type Il tipo della colonna.
         */
        Column(String name,String type){
            this.name=name;
            this.type=type;
        }

        /**
         * Restituisce il nome della colonna.
         *
         * @return Il nome della colonna.
         */
        public String getColumnName(){
            return name;
        }

        /**
         * Verifica se il tipo della colonna è di tipo numerico.
         *
         * @return {@code true} se il tipo è numerico, {@code false} altrimenti.
         */
        public boolean isNumber(){
            return type.equals("number");
        }

        /**
         * Restituisce una rappresentazione testuale della colonna nel formato "nome:tipo".
         *
         * @return Una stringa che rappresenta la colonna.
         */
        public String toString(){
            return name+":"+type;
        }

    }

    /** La lista delle colonne dello schema della tabella. */
    List<Column> tableSchema = new ArrayList<Column>();

    /**
     * Costruttore della classe TableSchema.
     *
     * @param db        L'accesso al database.
     * @param tableName Il nome della tabella di cui ottenere lo schema.
     * @throws SQLException Se si verifica un errore durante l'accesso al database.
     */
    public TableSchema(DbAccess db, String tableName) throws SQLException{
        this.db=db;
        HashMap<String,String> mapSQL_JAVATypes = new HashMap<String, String>();
        //http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
        mapSQL_JAVATypes.put("CHAR","string");
        mapSQL_JAVATypes.put("VARCHAR","string");
        mapSQL_JAVATypes.put("LONGVARCHAR","string");
        mapSQL_JAVATypes.put("BIT","string");
        mapSQL_JAVATypes.put("SHORT","number");
        mapSQL_JAVATypes.put("INT","number");
        mapSQL_JAVATypes.put("LONG","number");
        mapSQL_JAVATypes.put("FLOAT","number");
        mapSQL_JAVATypes.put("DOUBLE","number");



        Connection con = db.getConnection();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getColumns(null, null, tableName, null);

        while (res.next()) {

            //Se il tipo della colonna controllata è presente nell'HashMap, inserisci nel tableSchema una nuova colonna
            //formata dal nome della colonna e dal tipo mappato dall'HashMap
            if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
                tableSchema.add(new Column(res.getString("COLUMN_NAME"), mapSQL_JAVATypes.get(res.getString("TYPE_NAME"))));
        }

        res.close();
    }

    /**
     * Restituisce il numero di attributi (colonne) nella tabella.
     *
     * @return Il numero di attributi nella tabella.
     */
    public int getNumberOfAttributes(){
        return tableSchema.size();
    }

    /**
     * Restituisce la colonna all'indice specificato.
     *
     * @param index L'indice della colonna da restituire.
     * @return La colonna all'indice specificato.
     */
    public Column getColumn(int index){
        return tableSchema.get(index);
    }

}