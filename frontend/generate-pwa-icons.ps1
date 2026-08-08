Add-Type -AssemblyName System.Drawing

$sourcePath = ".\public\brand\header-symbol.png"
$outputDirectory = ".\public\icons"

$sizes = @(72, 96, 128, 144, 152, 192, 384, 512)

if (!(Test-Path $sourcePath)) {
    Write-Host "Imagem original não encontrada: $sourcePath"
    exit 1
}

if (!(Test-Path $outputDirectory)) {
    New-Item -ItemType Directory -Path $outputDirectory | Out-Null
}

$source = [System.Drawing.Image]::FromFile(
    (Resolve-Path $sourcePath)
)

foreach ($size in $sizes) {

    $bitmap = New-Object System.Drawing.Bitmap(
        $size,
        $size
    )

    $graphics = [System.Drawing.Graphics]::FromImage(
        $bitmap
    )

    $graphics.SmoothingMode =
        [System.Drawing.Drawing2D.SmoothingMode]::HighQuality

    $graphics.InterpolationMode =
        [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic

    $graphics.PixelOffsetMode =
        [System.Drawing.Drawing2D.PixelOffsetMode]::HighQuality

    # Fundo azul da identidade visual
    $backgroundColor =
        [System.Drawing.Color]::FromArgb(
            15,
            125,
            187
        )

    $graphics.Clear(
        $backgroundColor
    )

    # O símbolo ocupa 68% do ícone.
    # Essa margem é importante para "maskable icons".
    $logoSize = [int]($size * 0.68)

    $x = [int](($size - $logoSize) / 2)
    $y = [int](($size - $logoSize) / 2)

    $graphics.DrawImage(
        $source,
        $x,
        $y,
        $logoSize,
        $logoSize
    )

    $outputPath =
        "$outputDirectory\icon-${size}x${size}.png"

    $bitmap.Save(
        $outputPath,
        [System.Drawing.Imaging.ImageFormat]::Png
    )

    $graphics.Dispose()
    $bitmap.Dispose()

    Write-Host "Criado: $outputPath"
}

$source.Dispose()

Write-Host ""
Write-Host "Ícones PWA do Segunda Chance gerados com sucesso."
