name: Modify Other Repo

on:
  push:
    branches:
      - main  # 可以根据需要触发此 Action

jobs:
  modify-file:
    runs-on: ubuntu-latest
    steps:
      # 检出当前存储库（可选）
      - name: Checkout this repository
        uses: actions/checkout@v3

      # 检出另一个存储库
      - name: Checkout other repository
        uses: actions/checkout@v3
        with:
          repository: <your-username>/<other-repository>  # 目标存储库
          token: ${{ secrets.PAT_TOKEN }}                 # 使用刚刚创建的 Token
          path: other-repo                                # 设置检出的路径

      # 修改目标 YAML 文件
      - name: Modify YAML file
        run: |
          ls
          pwd
          cd other-repo && yq eval '.key = "new value2222"' -i path/to/yourfile.yaml  # 修改 YAML 文件的值
          pwd
      # 提交更改
      - name: Commit and push changes
        run: |
          cd other-repo
          git config --global user.name "github-actions[bot]"
          git config --global user.email "github-actions[bot]@users.noreply.github.com"
          git add path/to/yourfile.yaml
          git commit -m "Update YAML file"
          git push
        env:
          GITHUB_TOKEN: ${{ secrets.PAT_TOKEN }}  # 使用 Token 推送更改
