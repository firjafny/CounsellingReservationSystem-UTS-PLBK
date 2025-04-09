# 🧠 Counselling Reservation System (Java Component Based)

Sistem ini adalah aplikasi berbasis Java yang memungkinkan **Client** untuk melakukan reservasi ke **Psikolog** secara interaktif melalui terminal. Aplikasi ini menggunakan konsep **component-based design**, di mana setiap komponen memiliki interface `provide` dan `require` yang jelas.

---

## 🚀 Fitur Utama

### 👤 Client
- Melihat daftar psikolog dan spesialisasi
- Melakukan reservasi (dengan input nama, usia, jenis kelamin, kategori spesialisasi)
- Memilih jadwal konsultasi
- Membayar biaya reservasi (memilih metode pembayaran)
- Melihat riwayat reservasi
- Membatalkan reservasi aktif

### 🧑‍⚕️ Psikolog
- Login sebagai salah satu psikolog
- Melihat daftar reservasi aktif + status pembayaran
- Menandai reservasi sebagai selesai
- Melihat riwayat reservasi sebelumnya

---

### 💡 Catatan Pengembangan
Fitur yang menggunakan prinsip provide/require:
- ClientService implements IClientService → provide layanan untuk client.
- PsikologService implements IPsikologService → provide layanan untuk psikolog.
- CounsellingReservationSystem require kedua interface tersebut.

--- 

### 👨‍💻 Dibuat Oleh
- 2208107010059 - Firjatullah Afny Abus
- 2208107010069 - Azimah Al-Huda
- Mata Kuliah: Perangkat Lunak Berbasis Komponen
