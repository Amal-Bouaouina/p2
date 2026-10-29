package amal.bouaouina.mybestlocation;

public class Util {
    public static final String REQUEST_SMS="Envoyer moi votre position.";
    public static final String RESPONSE_SMS="Ma position est:";
    public static final String CHANNEL_ID="BestLocations_ID";

    public static String IP_SERVEUR="10.0.2.2";
    /**
     * emulateur 10.0.2.2
     * Reseau local Ipv4 ipconfig
     * hebergement www...
     */
    public static final String URL_GET_ALL_POSITIONS="http://"+IP_SERVEUR+"/servicephp/getall.php";

}
