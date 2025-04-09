package services;

import interfaces.IPsikologService;
import models.Psikolog;
import models.Reservasi;

import java.util.List;
import java.util.Scanner;

public class PsikologService implements IPsikologService {
    private Scanner scanner = new Scanner(System.in);
    private List<Reservasi> semuaReservasi;

    public PsikologService(List<Reservasi> semuaReservasi) {
        this.semuaReservasi = semuaReservasi;
    }

    public void menu(Psikolog psikolog) {
        while (true) {
            System.out.println("\n--- Menu Psikolog: " + psikolog.getNama() + " ---");
            System.out.println("1. Lihat reservasi aktif");
            System.out.println("2. Tandai selesai");
            System.out.println("3. Lihat riwayat reservasi");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pilih = scanner.nextInt(); scanner.nextLine();

            switch (pilih) {
                case 1:
                    lihatReservasiAktif(psikolog);
                    break;
                case 2:
                    tandaiSelesai(psikolog);
                    break;
                case 3:
                    lihatRiwayat(psikolog);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void lihatReservasiAktif(Psikolog psikolog) {
        System.out.println("\nReservasi Aktif:");
        for (Reservasi r : semuaReservasi) {
            if (r.getPsikolog().equals(psikolog) && !r.isSelesai()) {
                System.out.println(r);
            }
        }
    }

    private void tandaiSelesai(Psikolog psikolog) {
        System.out.print("Masukkan nama client: ");
        String nama = scanner.nextLine();
        for (Reservasi r : semuaReservasi) {
            if (r.getPsikolog().equals(psikolog)
                    && r.getClient().getNama().equalsIgnoreCase(nama)
                    && !r.isSelesai()) {
                r.setSelesai(true);
                System.out.println("Reservasi ditandai selesai.");
                return;
            }
        }
        System.out.println("Reservasi tidak ditemukan.");
    }

    private void lihatRiwayat(Psikolog psikolog) {
        System.out.println("\nRiwayat Reservasi:");
        for (Reservasi r : semuaReservasi) {
            if (r.getPsikolog().equals(psikolog) && r.isSelesai()) {
                System.out.println(r);
            }
        }
    }
}
