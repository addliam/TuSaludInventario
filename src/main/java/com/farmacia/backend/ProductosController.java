package com.farmacia.backend;

import java.sql.Connection;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liam
 */
public class ProductosController {
    private Connection conn;

    
    public ProductosController() {
        conn = new DbConfig().getConn();
    }

    /**
    * Retorna un List<String[]> los siguientes campos de todos los productos:
    * Producto_id, Producto_nombre, Producto_precio_compra, Categoria_id,
    * Producto_stock_actual, Producto_unidad_medida, Producto_cantidad_unidades
    * Deber ser usado en una sentencia try catch para manejo de errores
    * @param  
    * @return List<String[]>
    */
    public List<String[]> getBasicInfoProducts(){
        List<String[]> allProductsList = new ArrayList<String[]>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Producto_id, Producto_nombre, Producto_precio_compra, Categoria_id, Producto_stock_actual, Producto_unidad_medida, Producto_cantidad_unidades from productos");
            while (rs.next()) {         
                System.out.println(rs.getString(1));
                String[] productRowList = new String[7];
                for (int i=0;i<7;i++){
                    productRowList[i] = rs.getString(i+1);
                }
                System.out.println("PRODUCT ROW LIST FINISHED");
                allProductsList.add(productRowList);
            }
            
            for (int k=0;k<allProductsList.size();k++){
                String[] rowStringArray = allProductsList.get(k);
                String productInfo = String.join(" - ", rowStringArray);
                System.out.println(productInfo);
            }            
        } catch (Exception e) {
            System.out.println("ERROR ON LIST PRODUCTOS");
            System.out.println(e);
        }
        return allProductsList;
    }
    
    /**
    * Actualiza la informacion de un producto
    * Producto_id, Producto_nombre, Producto_precio_compra, Categoria_id,
    * Producto_stock_actual, Producto_unidad_medida, Producto_cantidad_unidades
    * Deber ser usado en una sentencia try catch
    * @param  productoNombre El nombre del producto actualizado
    * @param  productoDescripcion La descripcion del producto actualizada
    * @param  productoPrecioCompra El precio de compra del producto actualizado
    * @param  categoriaId El id de la categoria del producto actualizado
    * @param  productoStockActual Stock actual del producto actualizado. Se deben considerar efectos no deseados
    * @param  productoUnidadMedida Unidad de medida del producto actualizado
    * @param productoCantidadUnidades Cantidad de unidades de medida del producto actualizado
    */    
    public void updateProductInformationById(String productoNombre, String productoDescripcion, float productoPrecioCompra, int categoriaId, int productoStockActual, String productoUnidadMedida, int productoCantidadUnidades){
        int i=0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("select Producto_id, Producto_nombre, Producto_descripcion,  Producto_precio_compra, Categoria_id, Producto_unidad_medida, Producto_cantidad_unidades from productos where Producto_id = ?");
            
        } catch (Exception e) {
        }
    }
    
    /**
    * Retorna un String[] con los siguientes campos de todos los productos:
    * Producto_id, Producto_nombre, Producto_descripcion,  Producto_precio_compra,
    * Categoria_id, Producto_unidad_medida, Producto_cantidad_unidades
    * Deber ser usado en una sentencia try catch para manejo de errores
    * @param  productoCodigo El codigo o id del producto a buscar
    * @return String[]
    */
    public String[] getProductInfoByID(String productoCodigo){
        String [] productInfo = new String[7];
        // TODO: code here
        try {
            PreparedStatement pstmt = conn.prepareStatement("select Producto_id, Producto_nombre, Producto_descripcion,  Producto_precio_compra, Categoria_id, Producto_unidad_medida, Producto_cantidad_unidades from productos where Producto_id = ?");
            pstmt.setString(1, productoCodigo);
            ResultSet rs = pstmt.executeQuery();
            // 7 columns
            System.out.println("---------- GETTING PRODUCT FULL INFORMATION ----------");
            while (rs.next()){
                System.out.println(rs.getString("Producto_id"));
                productInfo[0] = rs.getString("Producto_id");
                productInfo[1] = rs.getString("Producto_nombre");
                productInfo[2] = rs.getString("Producto_descripcion");
                productInfo[3] = rs.getString("Producto_precio_compra");
                productInfo[4] = rs.getString("Categoria_id");
                productInfo[5] = rs.getString("Producto_unidad_medida");
                productInfo[6] = rs.getString("Producto_cantidad_unidades");
            }
        } catch (Exception e) {
            System.out.println("ERROR GETTING PRODUCT FULL INFO BY ID");
            System.out.println(e);
        }
        return productInfo;
    }
    
    /**
    * Retorna un String[] con los siguientes campos de todos los productos:
    * Producto_id, Producto_nombre, Producto_descripcion,  Producto_precio_compra,
    * Categoria_id, Producto_unidad_medida, Producto_cantidad_unidades
    * Deber ser usado en una sentencia try catch para manejo de errores
    * @param
    * @return String[]
    */    
    public String[] getProductNameIdPairs(){
        String[] allProductsNameIdPairsArray = null;
        List<String> allProductsNameIdPairsList = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Producto_id, Producto_nombre from productos");
            while (rs.next()){
                String element = rs.getString(2)+" - "+rs.getString(1);
                allProductsNameIdPairsList.add(element);
                System.out.println(element);
                allProductsNameIdPairsArray = allProductsNameIdPairsList.toArray(new String[allProductsNameIdPairsList.size()]);
            }
        } catch (Exception e) {
            System.out.println("ERROR GETTING PRODUCT NAME ID PAIRS");
            System.out.println(e);
        }
        return allProductsNameIdPairsArray;
    }
    
    /**
    * Inserta un nuevo producto a la base de datos, requiere los siguientes campos
    * String productoNombre, String productoDescripcion, float productoPrecioCompra,
    * int categoriaId, int productoStockActual, String productoUnidadMedida, int productoCantidadUnidades
    * @param  productoNombre El nombre del nuevo producto
    * @return 0 operacion exitosa, 1 operacion fallida
    */    
    public int insertNewProduct(String productoNombre, String productoDescripcion, float productoPrecioCompra, int categoriaId, int productoStockActual, String productoUnidadMedida, int productoCantidadUnidades){
        int exitCode = 1;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Productos(Producto_nombre,Producto_descripcion, Producto_precio_compra, Categoria_id, Producto_stock_actual, Producto_unidad_medida, Producto_cantidad_unidades) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, productoNombre);
            pstmt.setString(2, productoDescripcion);
            pstmt.setFloat(3, productoPrecioCompra);
            pstmt.setInt(4, categoriaId);
            pstmt.setInt(5, productoStockActual);
            pstmt.setString(6, productoUnidadMedida);
            pstmt.setInt(7, productoCantidadUnidades);
            int response = pstmt.executeUpdate();
            if (response>=0){
                exitCode = 0;
                System.out.println("INSERT PRODUCTS");
                System.out.println("-> "+response+" rows inserted.");
            }
            
        } catch (Exception e) {
            System.out.println("OCURRIO UN ERROR AL INSERTAR PRODUCTO");
            System.out.println(e);
        }
        return exitCode;
    }

}
