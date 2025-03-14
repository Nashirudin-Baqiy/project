@extends('layouts.app')

@section('content')
@section('header')
    @include('layouts.header')
@show

<div class="container-fluid header">
    <div class="container py-5 mb-3">
        <div class="d-flex flex-column flex-md-row align-items-center">
            <div class="flex-fill">
                <h1 class="fs-1 text-bolder header-desc text-center text-md-start mb-5 mb-lg-0 pe-lg-5">Platform
                    Pembelian
                    Material Bangunan dari Toko Terdekat Anda</h1>
            </div>
            <div class="flex-fill justify-content-center">
                <img src="{{ asset('img/mobile-1.png') }}" alt="">
            </div>
        </div>
    </div>

    <div class="container py-5 mb-5">
        <h1 class="text-dark fw-bolder mb-5 mb-md-4 fs-5 text-bolder">Didukung Oleh :</h1>
        <div class="sponsored d-flex flex-column flex-md-row align-items-center">
            <img class="mb-4 mb-md-0 me-md-4" src="{{ asset('img/cvup.png') }}" alt="CVUP">
            <img class="mb-4 mb-md-0 me-md-4" src="{{ asset('img/ipb.png') }}" alt="IPB">
            <img class="mb-4 mb-md-0 me-md-4" src="{{ asset('img/undip.png') }}" alt="UNDIP">
            <img class="mb-4 mb-md-0 me-md-4" src="{{ asset('img/unj.png') }}" alt="UNJ">
        </div>
    </div>
</div>

<div class="container py-5 mb-5">
    <div class="d-flex flex-column flex-md-row flex-md-wrap justify-content-around align-items-md-start align-items-center">
        <div class="features d-flex flex-column justify-content-center mb-4 text-danger">
            <img src="{{ asset('img/mobile-2.png') }}" alt="Pendaftaran Instan">
            <h2 class="text-bolder mt-4">Pendaftaran Instan</h2>
            <p class="text-wrap">Pembuatan akun online, dilengkapi dengan kemudahan pendaftaran melalui Google
                dan
                Facebook Account.</p>
        </div>
        <div class="features d-flex flex-column justify-content-center mb-4 text-danger">
            <img src="{{ asset('img/mobile-3.png') }}" alt="Pengiriman Cepat">
            <h2 class="text-bolder mt-4">Pengiriman Cepat</h2>
            <p class="text-wrap">Barang sampai di hari yang sama, biaya pengiriman mulai dari Rp0,00.
            </p>
        </div>
        <div class="features d-flex flex-column justify-content-center mb-4 text-danger">
            <img src="{{ asset('img/mobile-4.png') }}" alt="Transaksi Aman">
            <h2 class="text-bolder mt-4">Transaksi Aman</h2>
            <p class="text-wrap">Penjual akan menerima pembayaran ketika barang yang dikirm telah sampai dan
                diterima oleh pembeli.
            </p>
        </div>
        <div class="features d-flex flex-column justify-content-center mb-4 text-danger">
            <img src="{{ asset('img/mobile-5.png') }}" alt="Pembayaran Mudah">
            <h2 class="text-bolder mt-4">Pembayaran Mudah</h2>
            <p class="text-wrap">Memungkinkan pembeli membayar melalui Transfer Bank, Credit, Gerai, hingga
                E-Wallet.
            </p>
        </div>
        <div class="features d-flex flex-column justify-content-center mb-4 text-danger">
            <img src="{{ asset('img/mobile-6.png') }}" alt="Kualitas Terjamin">
            <h2 class="text-bolder mt-4">Kualitas Terjamin</h2>
            <p class="text-wrap">Pembeli dapat mengetahui kualitas barang melalui deskripsi, serta rekomendasi
                penjual berkualitas tinggi.
            </p>
        </div>
    </div>
</div>

<div class="container mb-5">
    <hr>
    <img width="90%" class="mx-auto d-block my-5" src="{{ asset('img/table.png') }}" alt="Table">
    <hr>
</div>

<div class="container-fluid py-5 px-0 mb-5">
    <img width="100%" src="{{ asset('img/roadmap.png') }}" alt="Roadmap">
</div>

<div class="container-fluid footer text-center">
    <h1 class="fs-2 text-bolder text-danger">Ada Pertanyaan?<br>Hubungi Kami</h1>

    <div class="container my-5">
        <div class="d-flex mx-auto py-3 my-4 justify-content-around align-items-center">
            <a class="text-decoration-none" href="#">
                <img height="30px" src="{{ asset('img/ic_email.png') }}" alt="Email Contact">
            </a>
            <a class="text-decoration-none" href="#">
                <img height="35px" src="{{ asset('img/ic_ig.png') }}" alt="Instagram Contact">
            </a>
            <a class="text-decoration-none" href="#">
                <img height="33px" src="{{ asset('img/ic_wa.png') }}" alt="Whatsapp Contact">
            </a>
        </div>
    </div>
</div>
@section('footer')
    @include('layouts.footer')
@show
@endsection
