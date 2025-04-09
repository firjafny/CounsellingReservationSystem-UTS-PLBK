package services;

import interfaces.IClientService;
import models.Client;
import models.Psikolog;
import models.Reservasi;

import java.util.*;

public class ClientService implements IClientService {
    private Scanner scanner = new Scanner(System.in);
    private List<Reservasi> semuaReservasi;
    private List<Psikolog> daftarPsikolog;

    public ClientService(List<Reservasi> semuaReservasi) {
        this.semuaReservasi = semuaReservasi;
        this.daftarPsikolog = Arrays.asList(
                new Psikolog("Dr. Andini", "Keluarga"),
                new Psikolog("Dr. Caleb", "Keluarga"),
                new Psikolog("Dr. Xavier", "Remaja"),
                new Psikolog("Dr. Rafi", "Remaja"),
                new Psikolog("Dr. Sinta", "Anak"),
                new Psikolog("Dr. Zayne", "Anak")
        );
    }

    public List<Psikolog> getDaftarPsikolog() {
        return daftarPsikolog;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Menu Client ---");
            System.out.println("1. Lihat daftar psikolog");
            System.out.println("2. Buat reservasi");
            System.out.println("3. Riwayat reservasi");
            System.out.println("4. Batalkan reservasi");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = scanner.nextInt(); scanner.nextLine();

            switch (pilih) {
                case 1:
                    lihatPsikolog();
                    break;
                case 2:
                    buatReservasi();
                    break;
                case 3:
                    lihatRiwayat();
                    break;
                case 4:
                    batalkanReservasi();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pilihan tidak valid :(");
            }
        }
    }

    private void lihatPsikolog() {
        System.out.println("\nDaftar Psikolog:");
        for (int i = 0; i < daftarPsikolog.size(); i++) {
            System.out.println((i + 1) + ". " + daftarPsikolog.get(i));
        }
    }

    private void buatReservasi() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("\n--- Buat Reservasi ---");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Usia: ");
        int usia = scanner.nextInt(); scanner.nextLine();
        System.out.print("Jenis Kelamin: ");
        String gender = scanner.nextLine();
    
        // --- Pilih kategori spesialisasi---
        System.out.println("Pilih kategori spesialisasi:");
        String[] kategori = {"Keluarga", "Remaja", "Anak"};
        for (int i = 0; i < kategori.length; i++) {
            System.out.println((i + 1) + ". " + kategori[i]);
        }
        System.out.print("Pilih: ");
        int pilihKategori = scanner.nextInt(); scanner.nextLine();
        if (pilihKategori < 1 || pilihKategori > kategori.length) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        String spesialisasi = kategori[pilihKategori - 1];
    
        // Filter psikolog sesuai spesialisasi
        List<Psikolog> cocok = new ArrayList<>();
        for (Psikolog p : daftarPsikolog) {
            if (p.getSpesialisasi().equalsIgnoreCase(spesialisasi)) {
                cocok.add(p);
            }
        }
    
        if (cocok.isEmpty()) {
            System.out.println("Tidak ada psikolog dengan spesialisasi tersebut.");
            return;
        }
    
        // Pilih psikolog dari yang cocok
        System.out.println("\nPsikolog tersedia:");
        for (int i = 0; i < cocok.size(); i++) {
            System.out.println((i + 1) + ". " + cocok.get(i).getNama());
        }
        System.out.print("Pilih psikolog: ");
        int psikoIndex = scanner.nextInt(); scanner.nextLine();
        if (psikoIndex < 1 || psikoIndex > cocok.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
        Psikolog psikologDipilih = cocok.get(psikoIndex - 1);
    
        // Pilih jam dari jadwalAvailable
        List<String> jamTersedia = psikologDipilih.getJadwalAvailable();
        if (jamTersedia.isEmpty()) {
            System.out.println("Tidak ada jadwal tersedia untuk psikolog ini.");
            return;
        }
        System.out.println("Jam tersedia:");
        for (int i = 0; i < jamTersedia.size(); i++) {
            System.out.println((i + 1) + ". " + jamTersedia.get(i));
        }
        System.out.print("Pilih jam: ");
        int jamIdx = scanner.nextInt(); scanner.nextLine();
        if (jamIdx < 1 || jamIdx > jamTersedia.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }
    
        String jam = jamTersedia.get(jamIdx - 1);
        psikologDipilih.hapusJadwal(jam);
    
        Client client = new Client(nama, usia, gender, spesialisasi);
        Reservasi reservasi = new Reservasi(client, psikologDipilih, jam);
    
        // --- Metode pembayaran ---
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Transfer Bank");
        System.out.println("2. E-Wallet");
        System.out.print("Pilih: ");
        int metode = scanner.nextInt(); scanner.nextLine();
        String metodeBayar = (metode == 2) ? "E-Wallet" : "Transfer Bank";
    
        reservasi.setMetodePembayaran(metodeBayar);
        reservasi.setSudahBayar(true);
    
        semuaReservasi.add(reservasi);
    
        System.out.println("\n--- Bukti Reservasi ---");
        System.out.println("Nama: " + client.getNama());
        System.out.println("Usia: " + client.getUsia());
        System.out.println("Kelamin: " + client.getJenisKelamin());
        System.out.println("Spesialisasi: " + client.getSpesialisasi());
        System.out.println("Psikolog: " + psikologDipilih.getNama());
        System.out.println("Jam: " + jam);
        System.out.println("Metode Pembayaran: " + metodeBayar);
        System.out.println("Status: Lunas");
        System.out.println("-----------------------");
    }
    

    private void lihatRiwayat() {
        System.out.println("\nRiwayat Reservasi:");
        for (Reservasi r : semuaReservasi) {
            System.out.println(r);
        }
    }

    private void batalkanReservasi() {
        lihatRiwayat();
        System.out.print("Masukkan nama Anda untuk membatalkan: ");
        String nama = scanner.nextLine();
        Iterator<Reservasi> iter = semuaReservasi.iterator();
        boolean ditemukan = false;
        while (iter.hasNext()) {
            Reservasi r = iter.next();
            if (r.getClient().getNama().equalsIgnoreCase(nama) && !r.isSelesai()) {
                iter.remove();
                ditemukan = true;
                System.out.println("Reservasi dibatalkan.");
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Reservasi tidak ditemukan atau sudah selesai.");
        }
    }
}
