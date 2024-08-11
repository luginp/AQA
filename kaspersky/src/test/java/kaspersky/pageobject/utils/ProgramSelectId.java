package kaspersky.pageobject.utils;


import kaspersky.pageobject.enums.ProductName;

import java.security.InvalidParameterException;


public class ProgramSelectId {
    static String getOsId(String osName) {
        String os;
        switch (ProductName.valueOf(osName.toUpperCase())) {
            case WINDOWS:
                os = ProductName.WINDOWS.getType();
                break;
            case MAC:
                os = ProductName.MAC.getType();
                break;
            case ANDROID:
                os = ProductName.ANDROID.getType();
                break;
            case IOS:
                os = ProductName.IOS.getType();
                break;
            default:
                throw new InvalidParameterException();
        }
        return os;
    }

    public static String getProductId(String osName, String productName) {
        String productId;
        switch (ProductName.valueOf(osName.toUpperCase())) {
            case WINDOWS:
                productId = ProductName.WINDOWS.getNeededId(productName);
                break;
            case MAC:
                productId = ProductName.MAC.getNeededId(productName);
                break;
            case ANDROID:
                productId = ProductName.ANDROID.getNeededId(productName);
                break;
            case IOS:
                productId = ProductName.IOS.getNeededId(productName);
                break;
            default:
                throw new InvalidParameterException();

        }
        return productId;
    }


}