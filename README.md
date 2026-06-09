# Tugas Pemrograman Perangkat Bergerak: Arsitektur Aplikasi (UI Layer & ViewModel)

Repositori ini dibuat untuk memenuhi tugas mata kuliah Pemrograman Perangkat Bergerak - Implementasi Arsitektur Modern Android (UDF, ViewModel, dan StateFlow) pada aplikasi **Unscramble App**.

---

## 👤 Identitas Mahasiswa
* **Nama:** Abdullah Tsaqif Cakrawibowo
* **NIM:** 452024611036
* **Kelas:** TI 5 A1
* **Proyek:** Unscramble App (Permainan Acak Kata)

---

## 🛠️ Komponen & Fitur yang Diimplementasikan

Sesuai dengan rubrik penilaian, aplikasi ini telah mengimplementasikan arsitektur Android modern dengan pembagian layer yang jelas:

### 1. State Holder & UDF (Unidirectional Data Flow)
* **`GameUiState.kt`**: Berperan sebagai model data tunggal (*Single Source of Truth*) yang membungkus seluruh status UI (kata acak saat ini, jumlah kata, skor, status jawaban salah, dan status game over).
* **`GameViewModel.kt`**: Berperan sebagai pengelola logika bisnis yang memproses aksi pengguna (UI Events) dan memancarkan perubahan status melalui `StateFlow` secara aman.

### 2. UI Layer (Jetpack Compose)
* **`GameScreen.kt`**: Berisi UI deklaratif yang mendengarkan perubahan data dari `GameUiState` secara *real-time* menggunakan fungsi `.collectAsState()`. UI ini sepenuhnya terpisah dari logika bisnis permainan.
* **`MainActivity.kt`**: Berfungsi sebagai *entry point* utama yang memasang komponen `GameScreen()` ke dalam tema aplikasi.

### 3. Logika Permainan & Penanganan Rotasi Layar
* **Fungsi Skip & Submit**: Menampung input teks dari pengguna, mencocokkannya dengan kata asli, memperbarui skor jika benar, atau menampilkan pesan *error* jika salah.
* **Keamanan Data saat Rotasi (*Configuration Changes*)**: Karena status UI disimpan di dalam `ViewModel`, data permainan (seperti skor dan kata saat ini) **tidak akan hilang atau ter-reset** ketika layar handphone di-rotasi oleh pengguna.

---

## 🗂️ Struktur File Utama
```text
app/src/main/java/com/example/unscrambleapp/
│
├── MainActivity.kt            # Entry point aplikasi
└── ui/
    ├── GameUiState.kt         # Model representasi data UI
    ├── GameViewModel.kt       # Pengelola status & logika bisnis
    ├── GameScreen.kt          # Desain antarmuka Jetpack Compose
    └── theme/
        └── Theme.kt           # Tema dasar aplikasi
