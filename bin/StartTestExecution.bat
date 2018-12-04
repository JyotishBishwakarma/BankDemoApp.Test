set Script=%1
set TargetEnvironment=%2
set Browser=%3

IF "%1%" == "" (
    set Script=FlvUITest
	
) ELSE (
	set Script=%1%
)

IF "%2%" == "" (
    set TargetEnvironment=A
) ELSE (
	set TargetEnvironment=%2%
)



IF "%3%" == "" (
    set Browser=3
) ELSE (
	set Browser=%3%
)

echo mvn clean install  -Dtest=%Script% -DTargetEnvironment=%TargetEnvironment%  -Dselenium.browser=%Browser% -e
mvn clean install  -Dtest=%Script% -DTargetEnvironment=%TargetEnvironment%  -Dselenium.browser=%Browser% -e