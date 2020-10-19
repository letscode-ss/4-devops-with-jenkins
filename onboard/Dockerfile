FROM dwdraju/alpine-gcloud:latest
RUN apk update && apk add git 
RUN wget https://get.helm.sh/helm-v2.16.12-linux-amd64.tar.gz && \
    tar -xvzf helm-v2.16.12-linux-amd64.tar.gz  -C /usr/local/bin/ --strip-components=1
RUN wget https://dl.k8s.io/v1.19.0/kubernetes-client-linux-386.tar.gz && \
    tar -xvzf kubernetes-client-linux-386.tar.gz -C /usr/local/ --strip-components=2

RUN wget https://github.com/cli/cli/releases/download/v1.1.0/gh_1.1.0_linux_386.tar.gz && \
    tar -xvzf gh_1.1.0_linux_386.tar.gz  -C /usr/local/  --strip-components=1

