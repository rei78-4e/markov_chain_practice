{
  description = "markov_chain_practice dev shell";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    { nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        jdk = pkgs.jdk25;

        # Kuromoji (Apache License 2.0) from Maven Central. Not committed to the repo.
        kuromojiCore = pkgs.fetchurl {
          url = "https://repo1.maven.org/maven2/com/atilika/kuromoji/kuromoji-core/0.9.0/kuromoji-core-0.9.0.jar";
          hash = "sha256-newxfKsoeUdNOb/+pPP0gSiNh5Usm14Z1ol7DV4NNhM=";
        };
        kuromojiIpadic = pkgs.fetchurl {
          url = "https://repo1.maven.org/maven2/com/atilika/kuromoji/kuromoji-ipadic/0.9.0/kuromoji-ipadic-0.9.0.jar";
          hash = "sha256-JJCf11HAtDn3r1ExsIDrZbyFBi8MSXfKSnHHar504LY=";
        };
      in
      {
        devShells.default = pkgs.mkShell {
          packages = [ jdk ];

          shellHook = ''
            export JAVA_HOME=${jdk.home}
            export CLASSPATH=${kuromojiCore}:${kuromojiIpadic}''${CLASSPATH:+:$CLASSPATH}

            # .classpath (Eclipse) refers to lib/*.jar, so link the fetched jars there.
            mkdir -p lib
            ln -sfn ${kuromojiCore} lib/kuromoji-core-0.9.0.jar
            ln -sfn ${kuromojiIpadic} lib/kuromoji-ipadic-0.9.0.jar
          '';
        };
      }
    );
}
