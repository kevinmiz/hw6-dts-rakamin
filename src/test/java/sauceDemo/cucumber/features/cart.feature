Feature: User menambahkan barang ke keranjang

  Scenario: Login sebagai standar user dan tambah barang ke keranjang
    Given User telah login dan berada di home page
    When User click Add to Cart button pada salah satu barang
    And User click Add to Cart button pada barang yang berbeda
    And User click Add to Cart button pada barang yang berbeda
    And User click icon keranjang
    Then User diarahkan ke "Keranjang Kamu" halaman dan di keranjang seharusnya berisi barang yang user telah pilih

