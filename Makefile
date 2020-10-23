unbuild:
	remap
	decompile

remap:
	bin/tiny-remapper original/Allure-1.2.7.jar out/Allure-1.2.7-remapped.jar mappings/yarn-1.16.3+build.47-v2.tiny intermediary named

decompile:
	bin/fernflower out/Allure-1.2.7-remapped.jar out/ tmp/
	mv tmp/Allure-1.2.7-remapped.jar out/Allure-1.2.7-remapped-decompiled.jar	
