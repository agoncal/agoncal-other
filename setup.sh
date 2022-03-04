### Shell script setting things up on Mac



### Homebrew
### ########
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Tools
brew install ant
brew install httpie
brew install httping
brew install htop
brew install jq
brew install maven
brew install macvim
brew install pstree
brew install wget
brew install tree
brew install watch

# UI
brew cask install araxis-merge
brew cask install iterm2
brew cask install intellij-idea

# Browsers
brew cask install firefox
brew cask install google-chrome

# Cloud drives
brew cask install dropbox
brew install --cask onedrive

# Cloud, containers
brew install kubectl
brew cask install minishift

# Office stuff
brew cask install daisydisk
brew cask install libreoffice
brew cask install sizeup
brew cask install xmind
brew cask install xtrafinder

#Instant Messengers
brew cask install skype
brew cask install slack

# Media players
brew cask install vlc

# Readers
brew cask install kindle
brew cask install adobe-reader
brew cask install BBedit
brew cask install skim # PDF



### SDKMan
### ######
curl -s "https://get.sdkman.io" | zsh

# Java
sdk install java 17.0.2-tem
sdk install java 11.0.14-tem
sdk install java 8.0.322-tem

sdk install java 17.0.2-ms
sdk install java 11.0.14-ms

sdk install java 22.0.0.2.r17-grl

sdk install jbang



### Oh My ZSH
### #########

sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

## .zshrc
ZSH_THEME="af-magic"
zstyle ':omz:update' mode reminder
zstyle ':omz:update' frequency 13
plugins=(git macos autojump colored-man-pages copyfile docker httpie oc copypath docker history kubectl man mvn node sdk terraform vscode)

# User configuration

export CODE=~/Documents/Code
export TOOLS=~/Tools
export SHELL=~/Shells
export TEMP=~/Temp
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

## .zshrc
