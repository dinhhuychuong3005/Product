package com.warehouse.presentation;

import com.warehouse.dal.WareHouseDB;
import com.warehouse.model.WareHouse;
import com.warehouse.service.WareHouseService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class MainWareHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WareHouseService wareHouseService = new WareHouseService();
        try {
            wareHouseService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int choose;
        do {
            creatMenu();
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    try {
                        printProductList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        addProduct();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        editInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    findProduct();
                    break;
                case 6:
                    statusProduct();
                    break;
                case 7:
                    printProductEXPList();
                    break;
                case 8:
                    sortProductList();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Nhập lại:");
                    break;
            }

        }while (choose != 0);
    }

    public static void creatMenu(){
        System.out.println("------ QUẢN LÝ KHO HÀNG ------");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách hàng hóa trong kho.");
        System.out.println("2. Thêm hàng hóa vào kho.");
        System.out.println("3. Chỉnh sửa thông tin hàng hóa.");
        System.out.println("4. Xóa hàng hóa.");
        System.out.println("5. Tìm kiếm theo mã hàng hóa.");
        System.out.println("6. Xem tình trạng hàng hóa theo mã.");
        System.out.println("7. Xem tình trạng tất cả hàng hóa trong kho.");
        System.out.println("8. Sắp xếp hàng hóa theo tên.");
        System.out.println("0. Thoát.");
    }

    public static void printProductList() throws Exception{
        WareHouseService wareHouseService = new WareHouseService();
        wareHouseService.printData();
        System.out.println("Số lượng đang có trong kho: " + wareHouseService.size());
    }

    public static void printProductEXPList(){
        WareHouseService wareHouseService = new WareHouseService();
        wareHouseService.printEXP();
        System.out.println("Có...hàng sắp hết hạn");
    }

    public static void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        WareHouseService wareHouseService = new WareHouseService();
        System.out.println("Nhập vào mã SP muốn xóa khỏi kho hàng: ");
        String maSP = scanner.nextLine();
        WareHouse wareHouse = wareHouseService.find(maSP);
        if (wareHouse == null){
            System.out.println("Không tìm thấy sản phẩm!");
        }else {
            System.out.println(wareHouse.toString());
            try {
                wareHouseService.remove(maSP);
            } catch (Exception e) {
                e.printStackTrace();
            }System.out.println("Đã xóa " + wareHouse.getName() + " khỏi kho hàng");
        }
        WareHouseDB wareHouseDB = new WareHouseDB();
        try {
            wareHouseDB.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addProduct() throws Exception{
        WareHouseService wareHouseService = new WareHouseService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào thông tin sản phẩm:");

        System.out.println("Mã SP:");
        String productCode = scanner.nextLine();

        System.out.println("Tên sản phẩm:");
        String productName = scanner.nextLine();

        System.out.println("Số lượng:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Đơn giá:");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        String nsx;
        System.out.println("Ngày sản xuất(dd/mm/yyyy):");

        do {
            int year,month,day;
            nsx = scanner.nextLine();
            String [] s = nsx.split("/");
           day = Integer.parseInt(s[0]);
           month = Integer.parseInt(s[1]);
           year = Integer.parseInt(s[2]);
           LocalDate nsx1 = LocalDate.of(year,month,day);
           if (wareHouseService.checkInputDateMonthYear(nsx) == false ){
               System.out.println("Mời bạn nhập lại ngày sản đúng định dạng");
           }
        }while (wareHouseService.checkInputDateMonthYear(nsx) == false );
        String hsd;
        System.out.println("Hạn sử dụng(dd/mm/yyyy):");
        do {
            int year,month,day;

            hsd = scanner.nextLine();
        }while (wareHouseService.checkInputDateMonthYear(hsd) == false  );

        System.out.println("Nguồn gốc:");
        String origin = scanner.nextLine();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;
        long soNgayConLai = 0;
        try {
            String endDate = hsd;
            String startDate = simpleDateFormat.format(currentDate);

            date1 = simpleDateFormat.parse(startDate);
            date2 = simpleDateFormat.parse(endDate);

            long getDiff = date2.getTime() - date1.getTime();

             soNgayConLai = (getDiff / (24 * 60 * 60 * 1000) + 1);

        }catch (Exception e){
            e.printStackTrace();
        }
        WareHouse wareHouse = new WareHouse(productCode,productName,quantity,unitPrice,nsx,hsd,origin,soNgayConLai);
        WareHouse wareHouse1 = wareHouseService.find(productCode);
        if (wareHouse.equals(wareHouse1)){
            System.out.println("Sản phẩm đã có trong kho:");
            System.out.println(wareHouse1.toString());
        }else {
            wareHouseService.add(wareHouse);
            System.out.println("Đã thêm " + wareHouse.getName() + " vào kho hàng thành công!");
        }
    }

    public static void editInfo() throws Exception{
        Scanner scanner = new Scanner(System.in);
        WareHouseService wareHouseService = new WareHouseService();

        System.out.println("Nhập vào mã hàng cần chỉnh sửa thông tin:");
        String productCode = scanner.nextLine();

        WareHouse wareHouse = wareHouseService.find(productCode);
        if (wareHouse == null){
            System.out.println("Hàng hóa không có trong kho");
        }else {
            System.out.println("Thông tin hàng hóa:");
            System.out.println(wareHouse.toStringAll());

            System.out.println("Nhập thông tin mới:");
            System.out.println("Tên hàng hóa:");
            String productName = scanner.nextLine();
            wareHouse.setName(productName);

            System.out.println("Số lượng:");
            int quantity = scanner.nextInt();
            wareHouse.setQuantity(quantity);
            scanner.nextLine();

            System.out.println("Đơn giá:");
            double unitPrice = scanner.nextDouble();
            wareHouse.setUnitPrice(unitPrice);
            scanner.nextLine();

            String nsx;
            do {
                System.out.println("Ngày sản xuất(dd/mm/yyyy,dd-mm-yyyy hoặc dd.mm.yyyy):");
                nsx = scanner.nextLine();
            }while (wareHouseService.checkInputDateMonthYear(nsx) == false);
            wareHouse.setNsx(nsx);

            String hsd;
            do {
                System.out.println("Hạn sử dụng(dd/mm/yyyy,dd-mm-yyyy hoặc dd.mm.yyyy):");
                hsd = scanner.nextLine();
            }while (wareHouseService.checkInputDateMonthYear(hsd) == false);
            wareHouse.setHsd(hsd);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date endDate = simpleDateFormat.parse(hsd);
            Date startDate = new Date();
            long endValue = endDate.getTime();
            long startValue = startDate.getTime();
            long tmp = Math.abs(endValue - startValue);
            long soNgayConLai = tmp/(24*60*60*1000);
            wareHouse.setSoNgayConLai(soNgayConLai);

            System.out.println("Xuất xứ:");
            String origin = scanner.nextLine();
            wareHouse.setOrigin(origin);
            System.out.println("Cập nhật thành công!");
            System.out.println(wareHouse.toStringAll());
            wareHouseService.updateFile();
        }
    }

    public static void findProduct(){
        Scanner scanner = new Scanner(System.in);
        WareHouseService wareHouseService = new WareHouseService();

        System.out.println("Nhập mã hàng hóa cần tìm:");
        String productCode = scanner.nextLine();
        WareHouse wareHouse = wareHouseService.find(productCode);
        if (wareHouse == null){
            System.out.println("Hàng không có trong kho!");
        }else {
            System.out.println(wareHouse.toString());
        }
    }

    public static void exit(){
        System.out.println("Đã thoát ứng dụng quản lý kho.");
        System.exit(0);
    }

    public static void statusProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào mã hàng hóa bạn muốn kiểm tra tình trạng:");
        String productCode = scanner.nextLine();

        WareHouseService wareHouseService = new WareHouseService();
        WareHouse wareHouse = wareHouseService.find(productCode);
        if (wareHouse == null){
            System.out.println("Hàng hóa không có trong kho");
        }else {
            System.out.println("Tình trạng hàng hóa:");
            System.out.println(wareHouse.toStringEXP());
        }
    }

    public static void sortProductList(){
        WareHouseService wareHouseService = new WareHouseService();
        System.out.println("Danh sách sau khi sắp xếp");
        wareHouseService.sortList();
    }
}
