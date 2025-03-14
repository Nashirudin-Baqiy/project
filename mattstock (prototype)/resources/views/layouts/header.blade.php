<nav class="navbar navbar-expand-lg navbar-dark bg-mattstock sticky-top">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="{{ asset('img/logo-nav.png') }}" height="60px" alt="Mattstock Logo">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav ms-auto">
                <a class="nav-link mx-2 text-center {{ Request::is('/') ? 'activated' : '' }}" aria-current="page" href="{{ url('/') }}">Home</a>
                <a class="nav-link mx-2 text-center {{ Request::is('FAQ') ? 'activated' : '' }}" href="{{ url('FAQ') }}">FAQ</a>
                <a class="nav-link btn btn-light rounded-pill px-4 mx-0 mx-lg-3 text-danger fw-bold" href="{{ url('survey') }}">Take a Survey</a>
            </div>
        </div>
    </div>
</nav>
