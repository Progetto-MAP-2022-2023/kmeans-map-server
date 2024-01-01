package database;

/**
 * Enumerazione che rappresenta i tipi di operazioni di aggregazione da eseguire su una colonna di una tabella del database.
 *
 * @author Daniele Grandolfo    (GitHub: dgrandolfo4)
 * @author Rosanna Fracchiolla  (GitHub: RosannaFracchiolla)
 */
public enum QUERY_TYPE {
    /** Rappresenta l'operazione di aggregazione MIN (minimo). */
    MIN,

    /** Rappresenta l'operazione di aggregazione MAX (massimo). */
    MAX;
}
