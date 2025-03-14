@extends('layouts.app')

@section('content')
@section('header')
    @include('layouts.header')
@show

<div class="container pt-2 pb-5">
    <h1 class="fs-2 text-center my-5">Frequently Asked Questions</h1>

    <div class="accordion" id="accordionExample">
        @php
            $questions = 6;
        @endphp
        @for ($i = 1; $i <= $questions; $i++)
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading{{ $i }}">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapse{{ $i }}" aria-expanded="false"
                        aria-controls="collapse{{ $i }}">
                        <strong>
                            Question #{{ $i }}
                        </strong>
                    </button>
                </h2>
                <div id="collapse{{ $i }}" class="accordion-collapse collapse"
                    aria-labelledby="heading{{ $i }}" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        Answer #{{ $i }}
                    </div>
                </div>
            </div>
        @endfor
    </div>
</div>

@section('footer')
    @include('layouts.footer')
@show
@endsection
