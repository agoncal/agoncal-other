### Shell script setting things up on Mac

### Variables in .zshrc
### #########

export CODE=~/Documents/Code
export TOOLS=~/Tools
export SHELL=~/Shells
export TEMP=~/Temp
export GRAALVM_HOME=${SDKMAN_DIR}/candidates/java/22.0.0.2.r17-grl



### Oh My ZSH
### #########

sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# powerlevel10k
git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k

# .zshrc
ZSH_THEME="powerlevel10k/powerlevel10k"
zstyle ':omz:update' mode reminder
zstyle ':omz:update' frequency 13
plugins=(git macos autojump colored-man-pages copyfile docker httpie oc copypath docker history kubectl man mvn node sdk terraform vscode)


### Git
### ###
git config --global --add user.name "Antonio Goncalves"
git config --global --add user.email "antonio.goncalves@gmail.com"
git config --global --add --bool push.autoSetupRemote true
git config --global --add alias.hist "log --pretty=format:'%h %ad | %s%d [%an]' --graph --date=short"
git config --global push.autoSetupRemote true


### Homebrew
### ########
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Tools
brew install ant
brew install gh
brew install graphviz
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

# Azure
brew install azure-cli

# UI
brew install --cask araxis-merge
brew install --cask iterm2
brew install --cask intellij-idea
brew install --cask visual-studio-code
brew install --cask plural
sight
# Browsers
brew install --cask firefox
brew install --cask google-chrome
brew install --cask microsoft-edge

# Cloud drives
brew install --cask dropbox
brew install --cask onedrive

# Cloud, containers
brew install kubectl
brew cask install minishift

# Office stuff
brew install --cask daisydisk
brew install --cask libreoffice
brew install --cask sizeup
brew install --cask xmind
brew install --cask xtrafinder

#Instant Messengers
brew install --cask skype
brew install --cask slack
brew install --cask whatsapp

# Media players
brew install --cask vlc

# Readers
brew install --cask kindle
brew install --cask adobe-reader
brew install --cask BBedit
brew install --cask skim 



### NVM
### ######

brew install nvm
mkdir ~/.nvm

# .zshrc
export NVM_DIR="$HOME/.nvm"

nvm install node
nvm install 12



### SDKMan
### ######
curl -s "https://get.sdkman.io" | zsh

# .zshrc
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"


# Java
sdk install java 17.0.2-tem
sdk install java 11.0.14-tem
sdk install java 8.0.322-tem

sdk install java 17.0.2-ms
sdk install java 11.0.15-ms

sdk install java 22.0.0.2.r17-grl

sdk install jbang

sdk install quarkus

### Mac OS
### ######

# Finder
defaults write com.apple.Finder AppleShowAllFiles true
killall Finder
