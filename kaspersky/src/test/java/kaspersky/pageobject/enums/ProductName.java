package kaspersky.pageobject.enums;

import java.security.InvalidParameterException;

public enum ProductName {
    WINDOWS("1") {
        @Override
        public String getNeededId(String product) {
            String productId;
            switch (ProgramName.valueOf(product.replace(" ", "_").toUpperCase())) {
                case TOTAL_SECURITY:
                    productId = "1955";
                    break;
                case SAFE_KIDS:
                    productId = "1773";
                    break;
                case INTERNET_SECURITY:
                    productId = "1954";
                    break;
                case ANTI_VIRUS:
                    productId = "1953";
                    break;
                case PASSWORD_MANAGER:
                    productId = "1514";
                    break;
                default:
                    throw new InvalidParameterException();
            }
            return productId;
        }
    },
    MAC("2") {
        @Override
        public String getNeededId(String product) {
            String productId;
            switch (ProgramName.valueOf(product.replace(" ", "_").toUpperCase())) {

                case SAFE_KIDS:
                    productId = "1774";
                    break;
                case INTERNET_SECURITY:
                    productId = "2006";
                    break;
                case PASSWORD_MANAGER:
                    productId = "1937";
                    break;
                default:
                    throw new InvalidParameterException();
            }
            return productId;
        }
    },
    ANDROID("3") {
        @Override
        public String getNeededId(String product) {
            String productId;
            switch (ProgramName.valueOf(product.replace(" ", "_").toUpperCase())) {
                case SAFE_KIDS:
                    productId = "1776";
                    break;
                case INTERNET_SECURITY:
                    productId = "1904";
                    break;
                case PASSWORD_MANAGER:
                    productId = "1623";
                    break;
                default:
                    throw new InvalidParameterException();
            }
            return productId;
        }
    },
    IOS("4") {
        @Override
        public String getNeededId(String product) {
            String productId;
            switch (ProgramName.valueOf(product.replace(" ", "_").toUpperCase())) {

                case SAFE_KIDS:
                    productId = "1622";
                    break;
                case PASSWORD_MANAGER:
                    productId = "1775";
                    break;
                default:
                    throw new InvalidParameterException();
            }
            return productId;
        }
    };

    private String type;

    public abstract String getNeededId(String id);

    ProductName(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
