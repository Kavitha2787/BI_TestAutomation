for /f tokens^=2-5^ delims^=.-_^" %j in ('java -fullversion 2^>^&1') do @set "jver=%j%k%l%m"


