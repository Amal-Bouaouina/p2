package amal.bouaouina.mybestlocation;

public class Position {
    int idposition;
    String numero;
    String pseudo;
    String longitude;
    String latitude ;

    public Position(int idposition, String numero, String pseudo, String longitude, String latitude) {
        this.idposition = idposition;
        this.numero = numero;
        this.pseudo = pseudo;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "idposition=" + idposition +
                ", numero='" + numero + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
