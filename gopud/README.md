# Tutorial APAP

## Authors

* **Nadia Syafitri** - *1706022672* - *APAP-C*

## Tutorial 2

### What I have learned today

1. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
URL diatas tidak dapat terbuka karena sebelumnya kita belum mempunyai file html "add-restoran" sehingga tidak muncul bentuk html yang dirender.

2. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
Halaman tersebut menjadi Whitelabel Error karena pada link tersebut kita tidak memasukan nomor telepon sehingga proses add tidak berhasil.

3. Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?
http://localhost:8080/restoran/view?idRestoran=1

4. Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan?
Sertakan juga bukti screenshotmu
Halaman akan menampilkan 2 restoran yang sudah diadd sebelumnya beserta id, alamat, dan nomor telepon dari masing-masing restoran tersebut.

   http://localhost:8080/restoran/add?idRestoran=1&nama=Nadin&alamat=PondokLabu%20Fasilkom&nomorTelepon=14045
   ![ImageDescription](https://i.ibb.co/h1C46ZW/message-Image-1568819763182.jpg)
