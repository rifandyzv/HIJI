public class Help {
    public Help(){

    }
    public void printHelp(){
        System.out.println("HIJI Helpdesk!");
        System.out.println("");
        System.out.println("Permainan dapat dimainkan oleh 2-6 orang.");
        System.out.println("Setiap pemain akan mendapatkan 7 kartu di awal permainan.");
        System.out.println("Satu kartu angka akan dipilih secara acak sebagai kartu pertama.");
        System.out.println("Pemain dapat mengeluarkan satu atau lebih kartu dengan warna, nomor, atau simbol yang sama dengan kartu sebelumnya.");
        System.out.println("Pemain diharapkan draw satu kartu jika tidak dapat mengeluarkan kartu.");
        System.out.println("Pemain dapat mengeluarkan kartu tersebut apabila dapat dikeluarkan.");
        System.out.println("Simbol skip akan melewati pemain selanjutnya.");
        System.out.println("Simbol reverse akan membalik urutan permainan.");
        System.out.println("Kartu wild dapat menentukan warna kartu yang dikeluarkan selanjutnya.");
        System.out.println("Kartu draw 2 dapat membuat pemain selanjutnya melakukan draw sebanyak 2 kartu.");
        System.out.println("Kartu draw 4 dapat membuat pemain selanjutnya melakukan draw sebanyak 4 kartu dan dapat menentukan warna kartu selanjutnya.");
        System.out.println("Pemain harus melakukan declare HIJI dalam waktu 3 detik ketika hanya memiliki satu kartu.");
        System.out.println("Pemain harus melakukan draw 2 kartu apabila tidak melakukan declare HIJI.");
        System.out.println("Pemain dinyatakan menang apabila kartu yang dipegang sudah habis dan permainan dinyatakan selesai.");
    }
}