# Tutorial APAP

## Authors

* **Nadia Syafitri** - *1706022672* - *APAP-C*

## Tutorial 1
### What I have learned today
#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
   
   Issues pada github adalah salah satu tracker yang ada pada GitHub yang berfungsi untuk mengumpulkan feedback dari user,        report software bugs dan kita bisa mengatur tugas-tugas apa yang harus kita kerjakan untuk memperbaiki sebuah masalah dalam 
   repository. Masalah yang dapat diselesaikan dengan Issues adalah menyelesaikan bugs atau error dan menyelesaikan merge        request juga.
2. Apa perbedaan dari git merge dan merge --squash?
   
   Perbedaan git merge dan merge --squash dijelaskan pada gambar dibawah ini:
   ![Image description](https://help.github.com/assets/images/help/pull_requests/standard-merge-commit-diagram.png)
   ![Image description](https://help.github.com/assets/images/help/pull_requests/commit-squashing-diagram.png)

#### Spring
3. Apa itu library & dependency?
   
   Library adalah set of code yang sebelumnya telah dituliskan oleh seseorang sebelumnya, dan bisa kita panggil atau gunakan      saat kita sedang membuat program kita sendiri.
   Dependency adalah suatu ketergantungan fungsionalitas antara suatu objek dengan objek lainnya, sehingga apabila terjadi        perubahan pada salah satunya akan berdampak kepada objek lainnya.
4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
   
   Maven adalah Java Build Tools yang menggunakan konsep Project Object Model (POM). Maven memiliki beberapa keuntungan          yaitu :
      - Maven membuat struktur project sendiri sehingga project tersebut akan dapat dibuka dengan berbagai IDE karenakan               Maven mendefinisikan projectnya sendiri. Project ini bisa dibuka di intelliJ IDE atau juga STS.
      - Mengatur dependency pada Maven juga lebih mudah.
      - Maven mempermudah kita dalam mengimport class-class yang memiliki dependency pada program kita dengan cara hanya perlu         mendefinisikan dependency tersebut dalam file POM.xml dan Maven akan otomatis mendownload file-file jar tersebut ke           dalam repository kita.
5. Apa alternatif dari Maven?
   
   Alternatif dari Maven adalah Gradle dan Ant.
  
### What I did not understand
- [ ] Beberapa fungsi syntax yang ada dalam tutorial ini. 
- [ ] Bagaimana cara kerja @GetMapping dan @Controller?
- [ ] Apa fungsi dari pow.xml mvnw dalam project yang kita punya?

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


