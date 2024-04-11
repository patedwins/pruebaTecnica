# Build and push helm image

### Update dependencies
helm dependency update ./ci/helm
Ó
helm dep update

### Package helm chart 
helm package ./ci/helm
