## How to contribute
We'd love to accept your contributions to this App. There are just a few steps you need to follow.

> [!IMPORTANT]
> You must point your PR to the `develop` Branch.
> Update your branch develop in your local repository before push

## Branching
* fix/ -> Bugs, small fixes like typos.
* feature/ -> New functionalities.
* chore/ -> Changes that not affect the production code like update dependencies.
* refactor/ -> Code refactor.
* test/ -> Add missing test.


## Pull request for review
When you clone this repository a git hook is going to be installed in your local git. The git hooks are:
* pre-commit.
* pre-push.

> [!NOTE]
> If you are using a Windows maching review the scripts:
> * [pre-commit-windows](https://github.com/TheBlackBit/Animemania/blob/master/scripts/pre-commit-windows).
> * [pre-push-windows](https://github.com/TheBlackBit/Animemania/blob/master/scripts/pre-push-windows).


#### Pre-commit
1. A script runs Ktlint format it is going to format your code before commit.
2. A script runs all the unit tests to verify that none of the tests are broken.

#### Pre-push
1. A script runs all UI test locally.
>[!WARNING]
>YOU MUST HAVE A EMULATOR OR PHYSICAL DEVICE CONNECTED OR RUNNING.


Please correct any failures before requesting a review.
