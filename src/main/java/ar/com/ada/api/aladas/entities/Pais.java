package ar.com.ada.api.aladas.entities;

public class Pais {

    public enum PaisEnum{

        ARGENTINA(32), ESTADOS_UNIDOS(840), VENEZUELA(862), COLOMBIA(170);
        

        private final int value;

        private PaisEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static PaisEnum parse(int id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
    
    public enum TipoDocuEnum{

        DNI(1), PASAPORTE(2);
        

        private final int value;

        private TipoDocuEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static TipoDocuEnum parse(int id) {
            TipoDocuEnum status = null; // Default
            for (TipoDocuEnum item : TipoDocuEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
    
}
