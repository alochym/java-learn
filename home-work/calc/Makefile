image:
	docker build -t hello-world:"$(version)" .

image-build-cached:
	docker build -f Dockerfile-mvn-cache -t hello-world-cached:"$(version)" .

# image-native:
# https://aws.amazon.com/blogs/opensource/using-graalvm-build-minimal-docker-images-java-applications/
# https://www.graalvm.org/22.2/reference-manual/native-image/guides/use-native-image-maven-plugin/